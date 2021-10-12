import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Variable expression class.
 */
public class Var implements Expression {
    private String variable;

    /**
     * Constructor.
     * @param variable variable name.
     */
    public Var(String variable) {
        this.variable = variable;
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        boolean flag;
        try {
            flag = assignment.get(this.variable);
        } catch (NullPointerException exception) {
            // doesn't exist in the assignment map.
            throw new Exception("Variable {" + variable + "} isn't contains in variable list.");
        }
        return flag;
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Expression is contains variable.");
    }

    @Override
    public List<String> getVariables() {
        List<String> varList = new ArrayList<>();
        varList.add(variable);
        return varList;
    }

    @Override
    public String toString() {
        return variable;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.compareTo(var) == 0) {
            
            return expression;
        } else {
            return this;
        }
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
