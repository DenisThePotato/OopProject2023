//207270521 Denis Mogilevsky
import java.util.HashMap;
import java.util.Map;

/**
 * @author Denis Mogilevsky.
 * Various tests.
 */
public class ExpressionTest {
    /**
     * main.
     * @param args not relevant.
     */
    public static void main(String[] args) {
        //(2x) + (sin(4y)) + (e^x)
        System.out.println("University tests:");
        Expression assignmentTest = new Plus(
                new Plus(
                        new Mult(
                                new Num(2), new Var("x")),
                        new Sin(
                                new Mult(new Num(4), new Var("y")))),
                new Pow(new Var("e"), new Var("x")));
        //Print the expression.
        System.out.println(assignmentTest);
        Map<String, Double> expressionMap = new HashMap<>();
        expressionMap.put("x", 2.0);
        expressionMap.put("y", 0.25);
        expressionMap.put("e", Math.E);
        //Print the value of the expression with (x=2,y=0.25,e=2.71).
        try {
            System.out.println(assignmentTest.evaluate(expressionMap));
        } catch (Exception o) {
            System.out.println("Exception occurred: " + o.getMessage());
            o.printStackTrace();
        }
        //Print the differentiated expression according to x.
        System.out.println(assignmentTest.differentiate("x"));
        //Print the value of the differentiated expression according to x with the assignment above.
        try {
            System.out.println(assignmentTest.differentiate("x").evaluate(expressionMap));
        } catch (Exception o) {
            System.out.println("Exception occurred: " + o.getMessage());
            o.printStackTrace();
        }
        //Print the simplified differentiated expression.
        System.out.println(assignmentTest.differentiate("x").simplify());
        System.out.println("test 7:");
        Expression test7 = new Plus(new Mult(new Num(2), new Var("x")), new Plus(new Sin(new Mult(new Num(4), new Var("y"))), new Pow(new Var("e"), new Var("x"))));
        System.out.println("base expression: " + test7);
        System.out.println("differentiate: " + test7.differentiate("x"));
        System.out.println("simplified differentiation: " + test7.differentiate("x").simplify());
        System.out.println("test 14:");
        Expression test14 = new Plus(new Plus(new Cos(new Var("x")), new Log(new Var("e"), new Var("x"))),
                new Plus(new Div(new Num(1), new Var("x")), new Sin(new Pow(new Var("x"), new Num(2)))));
        System.out.println("base expression: " + test14);
        System.out.println("differentiate: " + test14.differentiate("X"));
        System.out.println("test 20:");
        Expression test20 = new Sin(new Pow(new Var("e"), new Div(new Sin(new Var("x")), new Cos(new Var("x")))));
        System.out.println("base expression: " + test20);
        System.out.println("differentiate: " + test20.differentiate("x"));
    }
}
