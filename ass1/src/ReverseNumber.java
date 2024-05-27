// 207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * Prints the input backwards.
 */
public class ReverseNumber {
    /**
     * Validates the input and if valid reverses it. Otherwise, prints 0.
     * @param args Gets a integer.
     */
    public static void main(String[] args) {
        try {                                                          //Input validation.
            int n = Integer.parseInt(args[0]);
            System.out.println("reverse number: " + reverseNum(n));
        } catch (NumberFormatException e) {                            //In case the input is invalid.
            System.out.println("0");
        }
    }

    /**
     * Creates the reversed number.
     * @param n number to be reversed.
     * @return n reversed.
     */
    public static int reverseNum(int n) {
        int reversed = n % 10;                    //Sets up the first digit of the reversed number.
        while (n >= 10 || n <= -10) {             //Changes the reversed number.
            reversed *= 10;
            n /= 10;
            reversed += n % 10;
        }
        return reversed;
    }
}
