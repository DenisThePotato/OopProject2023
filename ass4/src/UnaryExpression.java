//207270521 Denis Mogilevsky
import java.util.List;

/**
 * @author Denis Mogilevsky
 * a class representing unary expressions.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    /**
     * getter.
     * @return expression.
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * setter.
     * @param expression expression.
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return (getOperation() + "(" + getExpression().toString() + ")");
    }

    /**
     * @return a list of the variables in the expression
     */
    public List<String> getVariables() {
        return expression.getVariables();
    }
}
