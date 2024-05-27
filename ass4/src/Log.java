//207270521 Denis Mogilevsky
import java.util.Map;

/**
 * @author Denis Mogilevsky
 * a class representing a logarithmic expression.
 */
public class Log extends BinaryExpression implements Expression {
    Log(Expression firstExpression, Expression secondExpression) {
        setFirstExpression(firstExpression);
        setSecondExpression(secondExpression);
        setOperation("Log");
    }
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment map.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (Math.log(
                getSecondExpression().evaluate(assignment)) / Math.log(getFirstExpression().evaluate(assignment)));
    }
    /**
     * @return a nice string representation of the expression.
     */
    @Override
    public String toString() {
        return (getOperation() + "(" + getFirstExpression().toString() + ", " + getSecondExpression().toString() + ")");
    }

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with
     * the provided expression (Does not modify the current expression).
     * @param var variable.
     * @param expression expression.
     * @return a new expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Log(getFirstExpression().assign(var, expression), getSecondExpression().assign(var, expression));
    }

    /**
     * log f(x) in the base of g(x) = ln f(x)   f'(x) * ln g(x)   g'(x) * ln f(x)      .
     *                                ------- = --------------- - ---------------
     *                                ln g(x)        f(x)               g(x)
     *                                          ---------------------------------
     *                                                       ln g(x) ^ 2
     * @param variable variable.
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String variable) {
        return new Div(
                new Minus(
                        new Mult(
                                new Div(
                                        getSecondExpression().differentiate(variable), getSecondExpression()),
                                new Log(new Num(Math.E), getFirstExpression())),
                        new Mult(new Div(
                                getFirstExpression().differentiate(variable), getFirstExpression()),
                                new Log(new Num(Math.E), getSecondExpression()))),
                new Pow(
                        new Log(
                                new Num(Math.E), getFirstExpression()), new Num(2)));
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression simplifiedFirstExpression = getFirstExpression().simplify();
        Expression simplifiedSecondExpression = getSecondExpression().simplify();
        if (simplifiedFirstExpression.toString().equals(simplifiedSecondExpression.toString())) {
            return new Num(1);
        }
        try {
            new Num(new Log(simplifiedFirstExpression, simplifiedSecondExpression).evaluate());
        } catch (Exception e) {
        }
        return new Log(simplifiedFirstExpression, simplifiedSecondExpression);
    }
}
