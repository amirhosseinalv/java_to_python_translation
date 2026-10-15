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
    
}
