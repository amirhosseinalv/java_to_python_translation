package ofp;

import java.util.HashMap;

import generated.ofpBaseVisitor;
import generated.ofpParser;

public class PythonGenerator extends ofpBaseVisitor<String> {

    private int depth = 0;
    private HashMap<Integer, String> indentCache;

    public PythonGenerator() {
        this.indentCache = new HashMap<>();
    }
}
