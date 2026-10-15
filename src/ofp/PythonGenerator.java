package ofp;

import generated.ofpBaseVisitor;
import java.util.HashMap;
import generated.ofpParser;

public class PythonGenerator extends ofpBaseVisitor<String> {

    private int depth = 0;
    private HashMap<Integer, String> indentCache;

    public PythonGenerator() {
        this.indentCache = new HashMap<>();
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
        // TODO: wrap in getSafePythonId(...) once Filippo pushes it
        String varName = ctx.ID().getText();
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
    
}
