import java.util.List;
import java.util.Map;

/**
 * Unary Expression class.
 */
public class UnaryExpression extends BaseExpression {
    private Expression base;

    /**
     * Constructor.
     * @param base base expression.
     */
    public UnaryExpression(Expression base) {
        this.base = base;
    }

    /**
     * get base expression.
     * @return expression.
     */
    public Expression getBase() {
        return base;
    }

    /**
     * set new base expression.
     * @param newBase new value.
     */
    public void setBase(Expression newBase) {
        this.base = newBase;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        for (String entry : getVariables()) {
            if (assignment.get(entry) == null) {
                throw new Exception();
            }
        }
        return this.base.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        if (getVariables().size() != 0) {
            throw new Exception();
        }
        return this.base.evaluate();
    }

    @Override
    public List<String> getVariables() {
        return this.base.getVariables();
    }

    @Override
    public String toString() {
        return "(" + this.base.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        this.base = this.base.assign(var, expression);
        return this;
    }
}
