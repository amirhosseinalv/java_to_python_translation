package ofp;

import generated.ofpBaseVisitor;
import generated.ofpParser;

import java.util.Arrays;
import java.util.HashMap;
import generated.ofpParser;
import java.util.HashSet;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class PythonGenerator extends ofpBaseVisitor<String> {

    private int depth = 0;
    private HashMap<Integer, String> indentCache;
    private ParseTreeProperty<OFPScope> scopes;
    private OFPScope currentScope;
    private TypeCheckVisitor typeEvaluator;
    private static HashSet<String> reservedIds = new HashSet<String>(Arrays.asList(
        "False", "None", "True", "and", "as", "assert", "async", "await", "break",
        "class", "continue", "def", "del", "elif", "else", "except", "finally",
        "for", "from", "global", "if", "import", "in", "is", "lambda", "nonlocal",
        "not", "or", "pass", "raise", "return", "try", "while", "with", "yield",
        "ArithmeticError", "AssertionError", "AttributeError", "BaseException",
        "BlockingIOError", "BrokenPipeError", "BufferError", "BytesWarning",
        "ChildProcessError", "ConnectionAbortedError", "ConnectionError",
        "ConnectionRefusedError", "ConnectionResetError", "DeprecationWarning",
        "EOFError", "Ellipsis", "EnvironmentError", "Exception", "False",
        "FileExistsError", "FileNotFoundError", "FloatingPointError",
        "FutureWarning", "GeneratorExit", "IOError", "ImportError",
        "ImportWarning", "IndentationError", "IndexError", "InterruptedError",
        "IsADirectoryError", "KeyError", "KeyboardInterrupt", "LookupError",
        "MemoryError", "NameError", "None", "NotADirectoryError", "NotImplemented",
        "NotImplementedError", "OSError", "OverflowError", "PendingDeprecationWarning",
        "PermissionError", "ProcessLookupError", "RecursionError", "ReferenceError",
        "ResourceWarning", "RuntimeError", "RuntimeWarning", "StopAsyncIteration",
        "StopIteration", "SyntaxError", "SyntaxWarning", "SystemError",
        "SystemExit", "TabError", "TimeoutError", "True", "TypeError",
        "UnboundLocalError", "UnicodeDecodeError", "UnicodeEncodeError",
        "UnicodeError", "UnicodeTranslateError", "UnicodeWarning", "UserWarning",
        "ValueError", "Warning", "ZeroDivisionError", "__build_class__",
        "__debug__", "__doc__", "__import__", "__loader__", "__name__",
        "__package__", "__spec__", "abs", "all", "any", "ascii", "bin", "bool",
        "bytearray", "bytes", "callable", "chr", "classmethod", "compile",
        "complex", "copyright", "credits", "delattr", "dict", "dir", "divmod",
        "enumerate", "eval", "exec", "exit", "filter", "float", "format",
        "frozenset", "getattr", "globals", "hasattr", "hash", "help", "hex", "id",
        "input", "int", "isinstance", "issubclass", "iter", "len", "license",
        "list", "locals", "map", "max", "memoryview", "min", "next", "object",
        "oct", "open", "ord", "pow", "print", "property", "quit", "range", "repr",
        "reversed", "round", "set", "setattr", "slice", "sorted", "staticmethod",
        "str", "sum", "super", "tuple", "type", "vars", "zip"
    ));

    public PythonGenerator(ParseTreeProperty<OFPScope> scopes) {
        this.indentCache = new HashMap<>();
        this.scopes = scopes;
        this.typeEvaluator = new TypeCheckVisitor(scopes);
    }
    
    private String indent(int depthLevel) {
        if (!indentCache.containsKey(depthLevel)) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ".repeat(depthLevel * 4));
            indentCache.put(depthLevel, sb.toString());
            return sb.toString();
        }
        return indentCache.get(depthLevel);
    }

    // ===== Statements & structure (Amirhossein) =====

    @Override
    public String visitPrintStmt(ofpParser.PrintStmtContext ctx) {
        String exprText = visit(ctx.expr());
        String printText = ctx.getChild(0).getText();
        if (printText.equals("println")) {
            return indent(depth) + "print(" + exprText + ")" + "\n"; 
        } else {
            return indent(depth) + "print(" + exprText + ", end='')" + "\n";
        }
    }

    @Override 
    public String visitVarDecl(ofpParser.VarDeclContext ctx) {
        String varName = getSafePythonId(ctx.ID().getText());
        String exprText;
        if (ctx.expr() != null) {
            exprText = visit(ctx.expr());
        } else {
            // No initializer: Python needs a value, so pick a default for the type
            OFPType type = OFPType.get(ctx.type().getText());
            if (type == OFPType.INT) {
                exprText = "0";
            } else if (type == OFPType.FLOAT) {
                exprText = "0.0";
            } else if (type == OFPType.BOOL) {
                exprText = "False";
            } else if (type == OFPType.CHAR) {
                exprText = "''";
            } else {
                exprText = "\"\"";
            }
        }
        return indent(depth) + varName + " = " + exprText + "\n";
    }

    @Override
    public String visitArrayDecl(ofpParser.ArrayDeclContext ctx) {
        String varName = getSafePythonId(ctx.ID().getText());
        String exprText;
        if (ctx.expr() != null) {
            exprText = visit(ctx.expr());
        } else {
            // No initializer: an empty list still works with len()
            exprText = "[]";
        }
        return indent(depth) + varName + " = " + exprText + "\n";
    }

    // A block is one indentation level deeper than the statement that owns it
    @Override
    public String visitBlock(ofpParser.BlockContext ctx) {
        depth++;
        String body = "";
        for (ofpParser.StmtContext stmt : ctx.stmt()) {
            body = body + visit(stmt);
        }
        // Python does not allow an empty body, so {} becomes pass
        if (body.isEmpty()) {
            body = indent(depth) + "pass\n";
        }
        depth--;
        return body;
    }

    // The body of while/if/else is either a block or a single statement.
    // A block indents itself; a single statement needs us to indent it.
    private String visitBody(ParseTree body) {
        if (body instanceof ofpParser.BlockContext) {
            return visit(body);
        }
        depth++;
        String line = visit(body);
        depth--;
        return line;
    }

    @Override
    public String visitWhileStmt(ofpParser.WhileStmtContext ctx) {
        String cond = visit(ctx.expr());
        // Children: while ( expr ) body  ->  the body is child 4
        return indent(depth) + "while " + cond + ":\n" + visitBody(ctx.getChild(4));
    }

    @Override
    public String visitIfStmt(ofpParser.IfStmtContext ctx) {
        String cond = visit(ctx.expr());
        // Children: if ( expr ) body [else body]  ->  then-body is child 4, else-body is child 6.
        // We use positions because each body may be a stmt or a block, so stmt(0)/block(0) can't tell them apart.
        String result = indent(depth) + "if " + cond + ":\n" + visitBody(ctx.getChild(4));
        if (ctx.getChildCount() > 5) {
            result = result + indent(depth) + "else:\n" + visitBody(ctx.getChild(6));
        }
        return result;
    }

    // ===== Expressions & safe names (Filippo) =====

    private static String getSafePythonId(String id) {
        if (reservedIds.contains(id)) {
            return "ofp_" + id;
        } else {
            return id;
        }
    }

    @Override 
    public String visitIdExpr(ofpParser.IdExprContext ctx){
        return getSafePythonId(ctx.ID().getText());
    }

    //Literal and array OFP-Python transformations

    //INTs are the same in OFP and Python
    @Override
    public String visitIntExpr(ofpParser.IntExprContext ctx) {
        return ctx.INT().getText();
    }
    //FLOATs are the same in OFP and Python
    @Override
    public String visitFloatExpr(ofpParser.FloatExprContext ctx) {
        return ctx.FLOAT().getText();
    }
    //BOOLs are the same in OFP and Python with the difference of the first letter
    @Override
    public String visitBoolExpr(ofpParser.BoolExprContext ctx) {
        return ctx.BOOL().getText().equals("true") ? "True" : "False";
    }
    //CHARs are the same in OFP and Python
    @Override
    public String visitCharExpr(ofpParser.CharExprContext ctx) {
        return ctx.CHAR().getText();
    }
    //STRINGs are the same in OFP and Python
    @Override
    public String visitStringExpr(ofpParser.StringExprContext ctx) {
        return ctx.STRING().getText();
    }
    //OFP: {... , ... , ...}, Python: [... , ... , ...]
    @Override
    public String visitArrayLiteralExpr(ofpParser.ArrayLiteralExprContext ctx) {
        StringBuilder buf = new StringBuilder();
        for(int i = 0; i < ctx.expr().size(); i++){
            if(i > 0){
                buf.append(", ");
            }
            buf.append(visit(ctx.expr(i)));
        }
        return "[" + buf.toString() + "]";
    }



    //Its the same in OFP and python
    @Override 
    public String visitParenExpr(ofpParser.ParenExprContext ctx) {
        return "(" + visit(ctx.expr()) + ")";
    }
    //Its the same in OFP and python
    @Override
    public String visitNegExpr(ofpParser.NegExprContext ctx) {
        return "-" + visit(ctx.expr());
    }
    //Its the same in OFP and python
    @Override
    public String visitAddSubExpr(ofpParser.AddSubExprContext ctx) {
        return visit(ctx.expr(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.expr(1));
    }
    //OFP: 3/2 == 1, Python: 3/2 == 1.5
    @Override 
    public String visitMulDivExpr(ofpParser.MulDivExprContext ctx){
        String lhs = visit(ctx.expr(0));
        String op = ctx.getChild(1).getText();
        String rhs = visit(ctx.expr(1));
        //If it's a division i need to check if it's a int division
        if(op.equals("/")){
            OFPType leftType = typeEvaluator.visit(ctx.expr(0));
            //If it is I need to cast the result since in OFP it's automatic but in Python the result would be a float
            if(leftType == OFPType.INT){
                return "int(" + lhs + " / " + rhs + ")";            }
        }
        //If it was a multiplication it's fine
        return lhs + " " + op + " " + rhs;
    }
    //Its the same in OFP and python
    @Override
    public String visitCompareExpr(ofpParser.CompareExprContext ctx) {
        return visit(ctx.expr(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.expr(1));
    }
    //Its the same in OFP and python
    @Override
    public String visitIndexExpr(ofpParser.IndexExprContext ctx) {
        return visit(ctx.expr(0)) + "[" + visit(ctx.expr(1)) + "]";
    }
    //OFP: lenght, Python: len
    @Override
    public String visitLengthExpr(ofpParser.LengthExprContext ctx) {
        return "len(" + visit(ctx.expr()) + ")";
    }
    //OFP: new (int|float|char)[expr], Python: [0|0.0|'']*(expr)
    @Override
    public String visitNewArrayExpr(ofpParser.NewArrayExprContext ctx) {
        String defaultValue;
        switch (ctx.arrayType().getText()) {
            case "int":
                defaultValue = "0";
                break;
            case "float":
                defaultValue = "0.0";
                break;
            case "char":
                defaultValue = "''";
                break;
            default:
                throw new IllegalArgumentException("Unsupported array type: " + ctx.arrayType().getText());
        }
        return "[" + defaultValue + "]*(" + visit(ctx.expr()) + ")";
    }

    @Override
    public String visitFuncCall(ofpParser.FuncCallContext ctx) {
        StringBuilder result = new StringBuilder(getSafePythonId(ctx.ID().getText())).append("(");
        List<ofpParser.ExprContext> arguments = ctx.expr();
        for (int i = 0; i < arguments.size(); i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(visit(arguments.get(i)));
        }
        return result.append(")").toString();
    }

    @Override
    public String visitCallExpr(ofpParser.CallExprContext ctx) {
        return visit(ctx.funcCall());
    }

   
    
}
