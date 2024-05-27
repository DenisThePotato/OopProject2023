//207270521 Denis Mogilevsky
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Denis Mogilevsky
 * a class representing a variable expression.
 */
public class Var implements Expression {
    private String variableName;

    /**
     * constructor.
     * @param name name of variable.
     */
    Var(String name) {
        variableName = name;
    }

    /**
     * getter.
     * @return variable name.
     */
    public String getVariableName() {
        return this.variableName;
    }

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment map.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.get(getVariableName()) == null) {
            throw new Exception("Trying to calculate an expression without a variables value.");
        }
        return assignment.get(getVariableName());
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public double evaluate() throws Exception {
        throw new Exception("Trying to calculate an expression without a variables value.");
    }

    /**
     * @return a list of the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(this.getVariableName());
        return list;
    }

    /**
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return getVariableName();
    }

    /**
     * Returns a new expression in which all occurrences of the variable var are replaced with
     * the provided expression (Does not modify the current expression).
     * @param var variable.
     * @param expression expression.
     * @return a new expression.
     */
    public Expression assign(String var, Expression expression) { //the expression can be an expression or a number
        if (getVariableName().equals(var)) {
            return expression;
        }
        return new Var(getVariableName());
    }

    /**
     * @param variable variable.
     * @return Returns the expression tree resulting from differentiating the current expression
     * relative to variable `var`.
     */
    public Expression differentiate(String variable) {
        if (getVariableName().equals(variable)) {
            return new Num(1);
        }
        return new Num(0);
    }

    /**
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        return new Var(getVariableName());
    }
}
