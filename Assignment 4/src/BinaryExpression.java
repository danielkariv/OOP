import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Binary expression class.
 */
public class BinaryExpression extends BaseExpression {
    private Expression left;
    private Expression right;

    /**
     * Constructor.
     * @param left left expression.
     * @param right right expression.
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Get left expression.
     * @return left expression.
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * set new left expression.
     * @param newLeft new value.
     */
    public void setLeft(Expression newLeft) {
        this.left = newLeft;
    }

    /**
     * Get right expression.
     * @return right expression.
     */
    public Expression getRight() {
        return right;
    }

    /**
     * set new right expression.
     * @param newRight new value.
     */
    public void setRight(Expression newRight) {
        this.right = newRight;
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        for (String entry : getVariables()) {
            if (assignment.get(entry) == null) {
                throw new Exception();
            }
        }
        return this.left.evaluate(assignment) && this.right.evaluate(assignment);
    }
    @Override
    public Boolean evaluate() throws Exception {
       if (getVariables().size() != 0) {
           throw new Exception();
       }
       return this.left.evaluate() && this.right.evaluate();
    }
    @Override
    public List<String> getVariables() {
        List<String> varList = new ArrayList<>();
        varList.addAll(this.left.getVariables());
        varList.addAll(this.right.getVariables());
        return varList;
    }

    @Override
    public String toString() {
        return "(" + this.left.toString() + " ? " + this.right.toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        this.left = this.left.assign(var, expression);
        this.right = this.right.assign(var, expression);
        return this;
    }
}
