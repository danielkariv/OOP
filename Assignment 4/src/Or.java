import java.util.Map;

/**
 * Or expression class.
 */
public class Or extends BinaryExpression {
    /**
     * Constructor.
     * @param left left expression.
     * @param right right expression.
     */
    public Or(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + getLeft().toString() + " | " + getRight().toString() + ")";
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getLeft().evaluate(assignment) || getRight().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return getLeft().evaluate() || getRight().evaluate();
    }

    @Override
    public Expression nandify() {
        Expression left = getLeft().nandify();
        Expression right = getRight().nandify();
        return new Nand(new Nand(left, left), new Nand(right, right));
    }

    @Override
    public Expression norify() {
        Expression left = getLeft().norify();
        Expression right = getRight().norify();
        return new Nor(new Nor(left, right), new Nor(left, right));
    }

    @Override
    public Expression simplify() {
        /*
        x | 1 = 1
        x | 0 = x
        x | x = x
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
                return new Val(true);
            } else {
                return leftSimple;
            }
        } else if (leftVal != null && rightVal == null) {
            if (leftVal) {
                return new Val(true);
            } else {
                return rightSimple;
            }
        }
        return new Or(leftSimple, rightSimple);
    }
}
