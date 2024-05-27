// 207270521 Denis Mogilevsky

import java.util.Arrays;

/**
 * @author Denis Mogilevsky.
 * a program that finds a number in an array and prints its first and last appearances. if not found returns -1.
 */
public class TripletOfZero {
    /**
     * @param args an array of numbers the last of which will be searched for.
     */
    public static void main(String[] args) {
        int[] intArray = stringsToArray(args);
        int[] tripletOfNumbers = tripletOfZero(intArray);
        if (tripletOfNumbers == null) {
            System.out.println("the triplet is: -1");
        } else {
            Arrays.sort(tripletOfNumbers);
            if (isAsc(args)) {
                ascTripletPrint(tripletOfNumbers);
            } else {
                descTripletPrint(tripletOfNumbers);
            }
        }
    }

    /**
     * @param numbers gets converted into an integer array.
     * @return the new integer array.
     */
    public static int[] stringsToArray(String[] numbers) {
        int[] numArray = new int[numbers.length - 1];
        for (int index = 1; index < numArray.length; index++) {
            numArray[index - 1] = Integer.parseInt(numbers[index]);
        }
        return numArray;
    }

    /**
     * @param numbers the int array of the numbers being sorted.
     * @return a new array including either the 3 numbers or none in case of no match.
     */
    public static int[] tripletOfZero(int[] numbers) {
        for (int firstNumIndex = 0; firstNumIndex < numbers.length - 2; firstNumIndex++) {
            for (int secondNumIndex = firstNumIndex + 1; secondNumIndex < numbers.length - 1; secondNumIndex++) {
                for (int thirdNumIndex = secondNumIndex + 1; thirdNumIndex < numbers.length; thirdNumIndex++) {
                    if (numbers[firstNumIndex] + numbers[secondNumIndex] + numbers[thirdNumIndex] == 0) {
                        return new int[]{numbers[firstNumIndex], numbers[secondNumIndex], numbers[thirdNumIndex]};
                    }
                }
            }
        }
        return null;
    }

    /**
     *
     * @param numbers is sorted in ascending order.
     */
    public static void ascTripletPrint(int[] numbers) {
        System.out.print("the triplet is: [");
        for (int index = 0; index < numbers.length; index++) {
            if (index < numbers.length - 1) {
                System.out.print(numbers[index] + ", ");
            } else {
                System.out.println(numbers[index] + "]");
            }
        }
    }

    /**
     * @param numbers is sorted in descending order.
     */
    public static void descTripletPrint(int[] numbers) {
        System.out.print("the triplet is: [");
        for (int index = numbers.length - 1; index >= 0; index--) {
            if (index > 0) {
                System.out.print(numbers[index] + ", ");
            } else {
                System.out.println(numbers[index] + "]");
            }
        }
    }

    /**
     * checks if the user input is asc or desc.
     * @param order is the array containing asc or desc.
     * @return true if asc and false otherwise.
     */
    public static boolean isAsc(String[] order) {
    return (order[0].equals("asc"));
    }

}
