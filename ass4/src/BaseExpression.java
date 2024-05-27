//207270521 Denis Mogilevsky
import java.util.Collections;
import java.util.Map;

/**
 * @author Denis Mogilevsky
 * a class representing all expressions.
 */
public abstract class BaseExpression {
 private String operation;

    /**
     * getter.
     * @return operation.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * setter.
     * @param operation operation.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment map.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return evaluation result.
     * @throws Exception checked in main.
     */
    public double evaluate() throws Exception {
        return evaluate(Collections.emptyMap());
    }
}
