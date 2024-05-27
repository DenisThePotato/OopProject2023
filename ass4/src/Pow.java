//207270521 Denis Mogilevsky
import java.util.Map;

// 0^0 = 1 just like any number.
/**
 * @author Denis Mogilevsky
 * a class representing an exponentiation expression.
 */
public class Pow extends BinaryExpression implements Expression {
    Pow(Expression firstExpression, Expression secondExpression) {
        setFirstExpression(firstExpression);
        setSecondExpression(secondExpression);
        setOperation("^");
    }
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment map.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.pow(getFirstExpression().evaluate(assignment), getSecondExpression().evaluate(assignment));
    }

    /**
     * @return a nice string representation of the expression.
     */
    @Override
    public String toString() {
        return ("(" + getFirstExpression().toString() + getOperation() + getSecondExpression().toString() + ")");
    }

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with
     * the provided expression (Does not modify the current expression).
     * @param var variable
     * @param expression expression
     * @return a new expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Pow(getFirstExpression().assign(var, expression), getSecondExpression().assign(var, expression));
    }

    /**
     * (f(x)^g(x))' = f(x)^g(x) * (g'(x) * ln(f(x)) + (g(x) * f'(x) / f(x))).
     * @param variable variable.
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String variable) {
        return new Mult(
                new Pow(
                        getFirstExpression(), getSecondExpression()),
                new Plus(
                        new Div(
                                new Mult(
                                        getFirstExpression().differentiate(variable), getSecondExpression()),
                                getFirstExpression()),
                        new Mult(
                                getSecondExpression().differentiate(variable),
                                new Log(new Num(Math.E), getFirstExpression()))));
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression simplifiedFirstExpression = getFirstExpression().simplify();
        Expression simplifiedSecondExpression = getSecondExpression().simplify();
        try {
            return new Num(new Pow(simplifiedFirstExpression, simplifiedSecondExpression).evaluate());
        } catch (Exception e) {
        }
        return new Pow(simplifiedFirstExpression, simplifiedSecondExpression);
    }
}
