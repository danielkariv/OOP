import java.util.List;
import java.util.Map;

/**
 * Value expression class.
 */
public class Val implements Expression {
    private boolean value;

    /**
     * Constructor.
     * @param value new value.
     */
    public Val(boolean value) {
        this.value = value;
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.value;
    }
    @Override
    public Boolean evaluate() throws Exception {
        return this.value;
    }
    @Override
    public List<String> getVariables() {
        return null;
    }
    @Override
    public String toString() {
        if (value) {
            return "T";
        } else {
            return "F";
        }
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
