/**
 * Represents a function declaration in the symbol table. Extending OFPSymbol, 
 * it treats the inherited type as the return type and maintains an ordered list 
 * of OFPParamSymbol objects representing the function's signature.
 */

package ofp;
import java.util.ArrayList;
import java.util.List;

public class OFPFunctionSymbol extends OFPSymbol {

    private final List<OFPParamSymbol> params = new ArrayList<>();


    public OFPFunctionSymbol(String name, OFPType type) {
        super(name, type);
    }


    public void addParam(OFPParamSymbol param){
        params.add(param);
    }

    public List<OFPParamSymbol> getParams() {
        return params;
    }

    public List<OFPType> getParamTypes() {
        List<OFPType> paramTypes = new ArrayList<>();
        for (OFPParamSymbol param : params) {
            paramTypes.add(param.getType());
        }
        return paramTypes;
    }

    @Override
    public String toString() {
        return "FunctionSymbol(name=" + getName() + ", type=" + getType() + ", params=" + params + ")";
    }
}
