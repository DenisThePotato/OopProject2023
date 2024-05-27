// 207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * Calculates the power of a given number recursively and iteratively.
 */
public class Pow {
    /**
     * calculates x^n and prints the results using a recursive and iterative functions.
     * @param args two numbers, a base (x) and a power (n).
     */
    public static void main(String[] args) {
        long x = Long.parseLong(args[0]);                           //The base taken from args.
        long n = Long.parseLong(args[1]);                           //The power taken from args.
        System.out.println("recursive: " + powRecursive(x, n));     //Printing the result of the recursive function.
        System.out.println("iterative: " + powIter(x, n));          //Printing the result of the iterative function.
    }

    /**
     * @param x the base number.
     * @param n the power.
     * @return the result of x^n using a recursive function.
     */
    public static long powRecursive(long x, long n) {
        if (n == 0) {                                     //In case the power is 0.
            return 1;
        } else if (n == 1) {                              //base case.
            return x;
        } else {                                          //The calculation method.
            return (x * powRecursive(x, n - 1));
        }
    }

    /**
     * @param x the base number.
     * @param n the power.
     * @return the result of x^n using an iterative function.
     */
    public static long powIter(long x, long n) {
        if (x == 0 || x == 1 || n == 1) {                        //The mathematical conditions to return x.
            return x;
        } else if (n == 0) {                                     //The mathematical condition to return 1.
            return 1;
        }
        long result = x;                                         //The calculation method.
        for (int count = 1; count < n; count++) {
            result *= x;
        }
        return result;
    }
}


