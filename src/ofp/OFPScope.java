/**
 * Represents a lexical scope (or environment) in the code. It stores a map of 
 * symbols declared within that specific block and maintains a reference to its 
 * enclosing (parent) scope, enabling recursive hierarchical name resolution.
 */

package ofp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OFPScope {
    private OFPScope enclosingScope; // null if global (outermost) scope
    private Map<String, OFPSymbol> symbols = new LinkedHashMap<>();
    private final List<OFPScope> children = new ArrayList<>();
    private String scopeName;        // what this scope is called when printed

    public OFPScope(OFPScope enclosingScope){
        this.enclosingScope = enclosingScope;
        if(enclosingScope != null){
            enclosingScope.addChild(this);
            this.scopeName = "block";
        } else {
            this.scopeName = "global";   // only the outermost scope has no parent
        }
    }

    /** Used by the symbol table listener to name function scopes, e.g. "function main" */
    public void setScopeName(String scopeName){
        this.scopeName = scopeName;
    }

    public String getScopeName(){
        return scopeName;
    }

    public OFPSymbol resolveLocally(String name){
        return symbols.get(name);
    }

    public OFPSymbol resolve(String name) {
        OFPSymbol sym = symbols.get(name);
        if (sym != null) {
            return sym;
        } else if (enclosingScope != null) {
            return enclosingScope.resolve(name);
        } else {
            return null;
        }
    }
    
    public void define(OFPSymbol sym) { symbols.put(sym.getName(), sym); }

    public OFPScope getEnclosingScope() { return enclosingScope; }

    @Override 
    public String toString(){
        return symbols.toString();
    }

    public void addChild(OFPScope childScope){
        children.add(childScope);
    }

    public void printTree(int depth){
        System.out.println("- ".repeat(depth) + symbols.toString());
        for (OFPScope child : children){
            child.printTree(depth + 1);
        }
    }

    /**
     * Prints the scope tree in a readable, indented form: one line naming the scope,
     * then one line per symbol with its type, then the same for every nested scope.
     * Example:
     *   -global
     *       main : void
     *     -function main
     *         a : int[]
     */
    public void printSymbolTable(int depth){
        String indent = "  ".repeat(depth);
        System.out.println(indent + "-" + scopeName);

        for (OFPSymbol sym : symbols.values()){
            System.out.println(indent + "    " + sym.getName() + " : " + sym.getType());
        }
        for (OFPScope child : children){
            child.printSymbolTable(depth + 1);
        }
    }

}
