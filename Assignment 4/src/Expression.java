import java.util.List;
import java.util.Map;

/**
 * Interface for expression.
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values provided and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment values for the variables in the expression.
     * @return result of expression with given values.
     * @throws Exception when the assignment is missing a variable.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * Evaluate the expression, and return the result.
     * If the expression contains a variable, then an exception is thrown.
     * @return result of expression.
     * @throws Exception when the assignment is missing a variable.
     */
    Boolean evaluate() throws Exception;

    /**
     * get a list of all variables in the expression.
     * @return list of variables.
     */
    List<String> getVariables();

    /**
     * get a string representing the expression.
     * @return expression as a string.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the current expression).
     * @param var the variable name to replace.
     * @param expression the expression which replacing the variable.
     * @return a new expression with replaced variable by given expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     * @return converted expression to nand operations only.
     */
    Expression nandify();

    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     * @return converted expression to nor operations only.
     */
    Expression norify();

    /**
     * Returned a simplified version of the current expression.
     * @return simplified expression.
     */
    Expression simplify();
}
