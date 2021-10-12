import java.util.Map;

/**
 * Not expression class.
 */
public class Not extends UnaryExpression {
    /**
     * Constructor.
     * @param expression base expression.
     */
    public Not(Expression expression) {
        super(expression);
    }

    @Override
    public String toString() {
        return "~(" + getBase().toString() + ")";
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(super.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(super.evaluate());
    }

    @Override
    public Expression nandify() {
        return new Nand(getBase().nandify(), getBase().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(getBase().norify(), getBase().norify());
    }

    @Override
    public Expression simplify() {
        return new Not(getBase().simplify());
    }
}
