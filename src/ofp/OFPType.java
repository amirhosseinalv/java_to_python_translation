/**
 * Implements a Flyweight (or Type Registry) pattern to manage data types. It 
 * ensures only a single, globally accessible instance exists for each primitive 
 * type, allowing fast and efficient type comparisons using reference equality.
 */

package ofp;
import java.util.HashMap;
import java.util.Map;

public class OFPType {

    private final String name;
    private static final Map<String, OFPType> types = new HashMap<>();

    public static final OFPType INT = new OFPType("int");
    public static final OFPType FLOAT = new OFPType("float");
    public static final OFPType STRING = new OFPType("string");
    public static final OFPType BOOL = new OFPType("bool");
    public static final OFPType CHAR = new OFPType("char");
    public static final OFPType VOID = new OFPType("void");
    public static final OFPType INT_ARRAY = new OFPType("int[]");
    public static final OFPType FLOAT_ARRAY = new OFPType("float[]");
    public static final OFPType CHAR_ARRAY = new OFPType("char[]");
    public static final OFPType ERROR = new OFPType("error");


    private OFPType(String name) {
        this.name = name;
        types.put(name, this);
    }

    public String getName() {
        return name;
    }

    public static OFPType get(String name) {
        return types.get(name);
    }


    @Override 
    public String toString() {
        return name;
    }
}
