//207270521 Denis Mogilevsky
import java.util.Map;
import java.util.List;

/**
 * @author Denis Mogilevsky
 * an interface including expression .
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment map.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    double evaluate() throws Exception;

    /**
     * @return a list of the variables in the expression
     */
    List<String> getVariables();

    /**
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with
     * the provided expression (Does not modify the current expression).
     * @param var variable.
     * @param expression expression.
     * @return a new expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * @param var variable.
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}
