/**
 * The base class for any declared entity in the OFP language. It stores 
 * fundamental properties shared by all symbols, specifically the identifier 
 * name and its associated OFPType.
 */

package ofp;

public class OFPSymbol {
    private final String name;
    private final OFPType type;

    public OFPSymbol(String name, OFPType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public OFPType getType() {
        return type;
    }

    @Override 
    public String toString() {
        return "Symbol(name=" + name + ", type=" + type + ")";
    }

    
}
