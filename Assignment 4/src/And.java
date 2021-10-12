import java.util.Map;

/**
 * And expression class.
 */
public class And extends BinaryExpression {

    /**
     * Constructor.
     * @param left left expression.
     * @param right right expression.
     */
    public And(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + getLeft().toString() + " & " + getRight().toString() + ")";
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getLeft().evaluate(assignment) && getRight().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return getLeft().evaluate() && getRight().evaluate();
    }

    @Override
    public Expression nandify() {
        Expression left = getLeft().nandify();
        Expression right = getRight().nandify();
        return new Nand(new Nand(left, right), new Nand(left, right));
    }

    @Override
    public Expression norify() {
        Expression left = getLeft().norify();
        Expression right = getRight().norify();
        return new Nor(new Nor(left, left), new Nor(right, right));
    }

    @Override
    public Expression simplify() {
        /*
        x & 1 = x
        x & 0 = 0
        x & x = x
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
                return leftSimple;
            }
        } else if (leftVal == null && rightVal != null) {
            if (rightVal) {
                return leftSimple;
            } else {
                return new Val(false);
            }
        } else if (leftVal != null && rightVal == null) {
            if (leftVal) {
                return rightSimple;
            } else {
                return new Val(false);
            }
        }
        return new And(leftSimple, rightSimple);
    }
}
