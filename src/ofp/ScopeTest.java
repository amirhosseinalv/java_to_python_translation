/**
 * A standalone unit test for the symbol table infrastructure. It manually constructs 
 * a hierarchy of scopes (global, function, and block) and populates them with symbols 
 * to verify that hierarchical name resolution, local visibility rules, and symbol 
 * shadowing work correctly independent of the ANTLR parsing process.
 */

package ofp;

public class ScopeTest {
    public static void main(String[] args) {
        OFPScope globalScope = new OFPScope(null);
        OFPFunctionSymbol add = new OFPFunctionSymbol("add", OFPType.INT);

        OFPScope functionScope = new OFPScope(globalScope);

        OFPParamSymbol a = new OFPParamSymbol("a", OFPType.INT);
        OFPParamSymbol b = new OFPParamSymbol("b", OFPType.INT);
        add.addParam(a);
        add.addParam(b);
        functionScope.define(a);
        functionScope.define(b);
        globalScope.define(add);

        OFPScope blockScope = new OFPScope(functionScope);
        OFPSymbol x = new OFPSymbol("x", OFPType.FLOAT);
        blockScope.define(x);

        OFPSymbol c = new OFPSymbol("a", OFPType.STRING);
        functionScope.define(c);

        System.out.println("Global Scope: " + globalScope);
        System.out.println("resolv add in block: " + blockScope.resolve("add"));
        System.out.println("resolv locally add in block: " + blockScope.resolveLocally("add"));
        System.out.println("Function Scope resolv x: " + functionScope.resolve("x"));
        System.out.println("Block Scope resolv a: " + functionScope.resolve("a"));
        
    }
}
