//207270521 Denis Mogilevsky
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Denis Mogilevsky
 * a class representing a numeric expression.
 */
public class Num implements Expression {
    private double number;

    /**
     * constructor.
     * @param number number to be given to the new num.
     */
    Num(double number) {
        this.number = number;
    }
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment map.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getNumber();
    }

    /**
     * @return value of number.
     */
    public double getNumber() {
        return number;
    }


    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public double evaluate() throws Exception {
        return getNumber();
    }

    /**
     * @return a list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return (new ArrayList<String>());
    }

    /**
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return (Double.toString(number));
    }

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with
     * the provided expression (Does not modify the current expression).
     * @param var variable.
     * @param expression expression.
     * @return a new expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Num(this.number);
    }

    /**
     * @param variable variable.
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String variable) {
        return new Num(0);
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        return new Num(getNumber());
    }
}
