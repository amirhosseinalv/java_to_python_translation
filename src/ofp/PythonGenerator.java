package ofp;

import generated.ofpBaseVisitor;
import java.util.HashMap;

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

    
}
