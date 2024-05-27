//207270521 Denis Mogilevsky
import java.util.Map;

/**
 * @author Denis Mogilevsky
 * a class representing a cos expression.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * constructor.
     * @param expression expression.
     */
    Cos(Expression expression) {
        setExpression(expression);
        setOperation("Cos");
    }
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment map containing variable values.
     * @return value of expression.
     * @throws Exception checked in main.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (Math.cos(Math.toRadians(getExpression().evaluate(assignment))));
    }
    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with
     * the provided expression (Does not modify the current expression).
     * @param var variable to be replaces.
     * @param expression expression that replaces var.
     * @return a new expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Cos(getExpression().assign(var, expression));
    }

    /**
     * @param variable variable.
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String variable) {
        return new Mult(new Neg(new Sin(getExpression())), getExpression().differentiate(variable));
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression simplifiedExpression = getExpression().simplify();
        try {
            return new Num(new Cos(simplifiedExpression).evaluate());
        } catch (Exception e) {
        }
        return new Cos(simplifiedExpression);
    }
}
