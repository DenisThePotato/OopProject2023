//207270521 Denis Mogilevsky
import java.util.Map;

/**
 * @author Denis Mogilevsky
 * a class representing a subtraction expression.
 */
public class Minus extends BinaryExpression implements Expression {
    Minus(Expression firstExpression, Expression secondExpression) {
        setFirstExpression(firstExpression);
        setSecondExpression(secondExpression);
        setOperation("-");
    }
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment map.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (getFirstExpression().evaluate(assignment) - getSecondExpression().evaluate(assignment));
    }


    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with
     * the provided expression (Does not modify the current expression).
     * @param var variable.
     * @param expression expression.
     * @return a new expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Minus(getFirstExpression().assign(var, expression), getSecondExpression().assign(var, expression));
    }

    /**
     * @param variable variable.
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String variable) {
        return new Minus(getFirstExpression().differentiate(variable), getSecondExpression().differentiate(variable));
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression simplifiedFirstExpression = getFirstExpression().simplify();
        Expression simplifiedSecondExpression = getSecondExpression().simplify();
        if (simplifiedFirstExpression.toString().equals(simplifiedSecondExpression.toString())) {
            return new Num(0);
        }
        try {
            if (simplifiedFirstExpression.evaluate() == 0) {
                return new Neg(simplifiedSecondExpression);
            }
        } catch (Exception e) {
        }
        try {
            if (simplifiedSecondExpression.evaluate() == 0) {
                return simplifiedFirstExpression;
            }
        } catch (Exception e) {
        }
        try {
            return new Num(new Minus(simplifiedFirstExpression, simplifiedSecondExpression).evaluate());
        } catch (Exception e) {
        }
        return new Minus(simplifiedFirstExpression, simplifiedSecondExpression);
    }
}
