package ofp;

import generated.ofpParser;
import generated.ofpBaseListener;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.ParserRuleContext;

public class CheckRefListener extends ofpBaseListener{
    private ParseTreeProperty<OFPScope> scopes;
    private OFPScope currentScope;
    private OFPScope globalScope;
    private String currentFunction = "global";
    private int errorCount = 0;

    public CheckRefListener(ParseTreeProperty<OFPScope> scopes) {
        this.scopes = scopes;
    }


    //Set the current and global scope
    @Override 
    public void enterProgram(ofpParser.ProgramContext ctx){
        enterScope(ctx);
        globalScope = currentScope;
    }

    
    //Set the current scope and fucntion name as main
    @Override 
    public void enterMain(ofpParser.MainContext ctx){
        enterScope(ctx);
        currentFunction = "main";
    }

    //Go back to global scope and function name
    @Override 
    public void exitMain(ofpParser.MainContext ctx){
        exitScope();
        currentFunction = "global";
    }


    //Set the current scope and function name
    @Override 
    public void enterFuncDecl(ofpParser.FuncDeclContext ctx){
        enterScope(ctx);
        currentFunction = ctx.getChild(1).getText();
    }

    //Go back to global scope and function name
    @Override 
    public void exitFuncDecl(ofpParser.FuncDeclContext ctx){
        exitScope();
        currentFunction = "global";
    }


    //If the block is a function or the main we already set the correct scope
    @Override 
    public void enterBlock(ofpParser.BlockContext ctx){
        if((ctx.getParent() instanceof ofpParser.FuncDeclContext) || (ctx.getParent() instanceof ofpParser.MainContext)){
            return;
        } else{
            enterScope(ctx);
        }
    }

    //
    @Override 
    public void exitBlock(ofpParser.BlockContext ctx){
        if((ctx.getParent() instanceof ofpParser.FuncDeclContext) || (ctx.getParent() instanceof ofpParser.MainContext)){
            return;
        } else{
            exitScope();
        }
    }



    //Check for undeclared variables in the current scope or its partent(s)
    @Override 
    public void enterAssignStmt(ofpParser.AssignStmtContext ctx){
        String varName = ctx.getChild(0).getText();
        if(currentScope.resolve(varName) == null){
            errorCount++;
            System.out.println("Error (line " + ctx.getStart().getLine() + "): Undeclared '" + varName + "' variable used in '" + currentFunction + "' function");
        }
    }

    //Check for undeclared array variables in the current scope or its partent(s)
    @Override 
    public void enterArrayAssignStmt(ofpParser.ArrayAssignStmtContext ctx){
        String arrayName = ctx.getChild(0).getText();
        if(currentScope.resolve(arrayName) == null){
            errorCount++;
            System.out.println("Error (line " + ctx.getStart().getLine() + "): Undeclared '" + arrayName + "' array variable used in '" + currentFunction + "' function");
        }
    }

    //Check for undeclared value or array variables in expressions in the current scope or its partent(s)
    @Override 
    public void enterIdExpr(ofpParser.IdExprContext ctx){
        String varName = ctx.getChild(0).getText();
        if(currentScope.resolve(varName) == null){
            errorCount++;
            System.out.println("Error (line " + ctx.getStart().getLine() + "): Undeclared '" + varName + "' variable used in expression in '" + currentFunction + "' function");
        }
    }

    //We can check for functions directly in the global scope
    @Override 
    public void enterFuncCall(ofpParser.FuncCallContext ctx){
        String funcName = ctx.getChild(0).getText();
        OFPSymbol sym = globalScope.resolve(funcName);
        if((sym == null) || !(sym instanceof OFPFunctionSymbol)){
            errorCount++;
            System.out.println("Error (line " + ctx.getStart().getLine() + "): Undeclared '" + funcName + "' function call");
        }
    }



    //Helper methods
    private void enterScope(ParserRuleContext ctx){
        currentScope = scopes.get(ctx);
        if (currentScope == null) {
            throw new RuntimeException("No current scope in enterScope!");
        }
    }
    private void exitScope(){
        currentScope = currentScope.getEnclosingScope();
    }

    

    public int getErrorCount(){
        return errorCount;
    }

}
