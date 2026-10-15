/**
 * Represents a parameter within a function signature. It extends OFPSymbol 
 * strictly for semantic differentiation, allowing the compiler to easily 
 * distinguish parameters from standard local variables during type checking.
 */

package ofp;

public class OFPParamSymbol extends OFPSymbol {

    public OFPParamSymbol(String name, OFPType type) {
        super(name, type);
    }
    
    @Override 
    public String toString() {
        return "ParamSymbol(name=" + getName() + ", type=" + getType() + ")";
    }

}
