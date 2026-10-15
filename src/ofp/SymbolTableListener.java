/**
 * The core semantic analyzer extending ofpBaseListener. It traverses the parse
 * tree to populate the symbol table, managing lexical scopes, functions, and
 * parameters by dynamically creating and linking OFPScope and OFPSymbol objects.
 * It also checks for duplicates
 */

package ofp;
import generated.ofpParser;
import generated.ofpBaseListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class SymbolTableListener extends ofpBaseListener {
    private OFPScope currentScope = null;
    private OFPFunctionSymbol currentFunctionSymbol = null;
    private ParseTreeProperty<OFPScope> scopes = new ParseTreeProperty<OFPScope>();
    private int errorCount = 0;

    /**
     * Defines a symbol in the current scope, unless a symbol with the same name is
     * already declared in that same scope. Duplicates are reported (with the line
     * number) and not added to the symbol table, and the analysis continues.
     * @param kind the kind of declaration, used in the error message
     * @return true if the symbol was defined, false if it was a duplicate
     */
    private boolean define(OFPSymbol sym, String kind, ParserRuleContext ctx) {
        if (currentScope.resolveLocally(sym.getName()) != null) {
            errorCount++;
            System.out.println("Error (line " + ctx.getStart().getLine() + "): Duplicate '"
                    + sym.getName() + "' " + kind + " declaration");
            return false;
        }
        currentScope.define(sym);
        return true;
    }

    @Override
    public void enterProgram(ofpParser.ProgramContext ctx) {
        // enclosing scope == null for the global/program scope
        currentScope = new OFPScope(null);
        scopes.put(ctx, currentScope);
    }

    @Override
    public void enterFuncDecl(ofpParser.FuncDeclContext ctx) {
        String returnTypeName = ctx.getChild(0).getText();
        OFPType returnType = OFPType.get(returnTypeName);
        String functionName = ctx.getChild(1).getText();

        // If the function name was already used in the global scope, the symbol is
        // reported and not added, and currentFunctionSymbol stays null
        OFPFunctionSymbol funcSymbol = new OFPFunctionSymbol(functionName, returnType);
        if (define(funcSymbol, "function", ctx)) {
            currentFunctionSymbol = funcSymbol;
        }

        // Either way we enter the function scope
        currentScope = new OFPScope(currentScope);
        currentScope.setScopeName("function " + functionName);
        scopes.put(ctx, currentScope);
    }

    @Override
    public void exitFuncDecl(ofpParser.FuncDeclContext ctx) {
        currentScope = currentScope.getEnclosingScope();
        currentFunctionSymbol = null; // Reset the current function symbol when exiting the function declaration
    }

    @Override
    public void enterValueParam(ofpParser.ValueParamContext ctx) {
        String paramType = ctx.getChild(0).getText();
        OFPType paramOFPType = OFPType.get(paramType);
        String paramName = ctx.getChild(1).getText();

        OFPParamSymbol paramSymbol = new OFPParamSymbol(paramName, paramOFPType);
        // Only a parameter that was really declared belongs to the function's param list.
        // If the function itself was duplicate, currentFunctionSymbol is still null
        if (define(paramSymbol, "parameter", ctx) && currentFunctionSymbol != null) {
            currentFunctionSymbol.addParam(paramSymbol);
        }
    }

    // Same as enterValueParam method
    @Override
    public void enterArrayParam(ofpParser.ArrayParamContext ctx) {
        String paramType = ctx.getChild(0).getText() + "[]";
        OFPType paramOFPType = OFPType.get(paramType);
        String paramName = ctx.getChild(3).getText();

        OFPParamSymbol paramSymbol = new OFPParamSymbol(paramName, paramOFPType);
        if (define(paramSymbol, "array parameter", ctx) && currentFunctionSymbol != null) {
            currentFunctionSymbol.addParam(paramSymbol);
        }
    }

    @Override
    public void enterMain(ofpParser.MainContext ctx) {
        // Name and return type are fixed by the grammar: void main()
        OFPFunctionSymbol mainSymbol = new OFPFunctionSymbol("main", OFPType.VOID);
        if (define(mainSymbol, "function", ctx)) {
            currentFunctionSymbol = mainSymbol;
        }

        // Either way we enter the main scope
        currentScope = new OFPScope(currentScope);
        currentScope.setScopeName("function main");
        scopes.put(ctx, currentScope);
    }

    @Override
    public void exitMain(ofpParser.MainContext ctx) {
        currentScope = currentScope.getEnclosingScope();
        currentFunctionSymbol = null;
    }

    @Override
    public void enterBlock(ofpParser.BlockContext ctx) {
        // A function body block shares the scope opened by enterFuncDecl/enterMain,
        // so that a parameter and a variable with the same name collide
        if (isFunctionBody(ctx)) {
            return;
        }
        currentScope = new OFPScope(currentScope);
        scopes.put(ctx, currentScope);
    }

    @Override
    public void exitBlock(ofpParser.BlockContext ctx){
        if (isFunctionBody(ctx)) {
            return;
        }
        currentScope = currentScope.getEnclosingScope();
    }

    /** True if this block is the body of a function or of main (no scope of its own) */
    private boolean isFunctionBody(ofpParser.BlockContext ctx) {
        return ctx.getParent() instanceof ofpParser.FuncDeclContext
                || ctx.getParent() instanceof ofpParser.MainContext;
    }

    @Override
    public void enterVarDecl(ofpParser.VarDeclContext ctx){
        String varTypeString = ctx.getChild(0).getText();
        OFPType varType = OFPType.get(varTypeString);
        String varName = ctx.getChild(1).getText();

        define(new OFPSymbol(varName, varType), "variable", ctx);
    }

    // Same as enterVarDecl method
    @Override
    public void enterArrayDecl(ofpParser.ArrayDeclContext ctx){
        String arrayTypeString = ctx.getChild(0).getText() + "[]";
        OFPType arrayType = OFPType.get(arrayTypeString);
        String arrayName = ctx.getChild(3).getText();

        define(new OFPSymbol(arrayName, arrayType), "array variable", ctx);
    }


    public ParseTreeProperty<OFPScope> getScopes(){
        return scopes;
    }

    public int getErrorCount(){
        return errorCount;
    }

}
