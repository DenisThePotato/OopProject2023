//207270521 Denis Mogilevsky
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Denis Mogilevsky
 * a class representing binary expressions.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression firstExpression;
    private Expression secondExpression;

    /**
     * getter.
     * @return first expression.
     */
    public Expression getFirstExpression() {
        return firstExpression;
    }

    /**
     * getter.
     * @return second expression.
     */
    public Expression getSecondExpression() {
        return secondExpression;
    }

    /**
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return ("(" + getFirstExpression().toString() + " " + getOperation() + " "
                + getSecondExpression().toString() + ")");
    }

    /**
     * setter.
     * @param firstExpression first expression.
     */
    public void setFirstExpression(Expression firstExpression) {
        this.firstExpression = firstExpression;
    }

    /**
     * setter.
     * @param secondExpression second expression.
     */
    public void setSecondExpression(Expression secondExpression) {
        this.secondExpression = secondExpression;
    }

    /**
     * @return a list of the variables in the expression
     */
    public List<String> getVariables() {
        List<String> list1 = getFirstExpression().getVariables();
        List<String> list2 = getSecondExpression().getVariables();
             list1.addAll(list2);
             if (!(list1.isEmpty())) {
                 Set<String> set = new HashSet<>(list1);
                 list1.clear();
                 list1.addAll(set);
                 return list1;
             }
         return list1;
    }
}
