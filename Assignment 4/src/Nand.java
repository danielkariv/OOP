import java.util.Map;

/**
 * Nand expression class.
 */
public class Nand extends BinaryExpression {
    /**
     * Constructor.
     * @param left left expression
     * @param right right expression.
     */
    public Nand(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + getLeft().toString() + " A " + getRight().toString() + ")";
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getLeft().evaluate(assignment) && getRight().evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(getLeft().evaluate() && getRight().evaluate());
    }

    @Override
    public Expression nandify() {
        Expression left = getLeft().nandify();
        Expression right = getRight().nandify();
        return new Nand(left, right);
    }

    @Override
    public Expression norify() {
        Expression left = getLeft().norify();
        Expression right = getRight().norify();
        return new Nor(new Nor(new Nor(left, left), new Nor(right, right)),
                        new Nor(new Nor(left, left), new Nor(right, right)));
    }

    @Override
    public Expression simplify() {
        /*
        x ↑ 1 = ~(x)
        x ↑ 0 = 1
        x ↑ x = ~(x)
        */
        // try to evaluate the expression, if can, return the value.
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
        }
        Boolean leftVal = null, rightVal = null;
        Expression leftSimple = getLeft().simplify(), rightSimple = getRight().simplify();
        try {
            leftVal = leftSimple.evaluate();
        } catch (Exception e) {
        }
        try {
            rightVal = rightSimple.evaluate();
        } catch (Exception e) {
        }
        if (leftVal == null && rightVal == null) {
            // has variables, check if same expression.
            if (leftSimple.toString().compareTo(rightSimple.toString()) == 0) {
                return new Not(leftSimple);
            }
        } else if (leftVal == null && rightVal != null) {
            if (rightVal) {
                return new Not(leftSimple);
            } else {
                return new Val(true);
            }
        } else if (leftVal != null && rightVal == null) {
            if (leftVal) {
                return new Not(rightSimple);
            } else {
                return new Val(true);
            }
        }
        return new Nand(leftSimple, rightSimple);
    }
}
