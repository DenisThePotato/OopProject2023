// 207270521 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * Checks where the last number of args first and last appears.
 */
public class PlaceInArray {
    /**
     * Creates an integer array from args and checks if and where the last arg appears between.
     * @param args gets converted into an int array.
     */
    public static void main(String[] args) {
    int[] numArray = new int[args.length];
    for (int index = 0; index < args.length - 1; index++) {                          //creates int array from arg input.
        numArray[index] = Integer.parseInt(args[index]);
    }
    numArray[numArray.length - 1] = -1;                                 //the last index is used to store the num index.
    int searchedNumber = Integer.parseInt(args[args.length - 1]);        //creates var representing the number searched.
    System.out.println(searchedNumber + " starts in " + placeInArray(searchedNumber, numArray)
            + " and ends in " + numArray[numArray.length - 1]);                                    //prints the results.
    }

    /**
     * Checks where the number is found in the array. the first index is returned and the second
     * is inserted in the last index of the array. If not found the values are -1.
     * @param n the number being searched for in the array.
     * @param array the array including the numbers.
     * @return the first index found.
     */
    public static int placeInArray(int n, int[] array) {
        int foundIndex = -1;
    for (int index = 0; (index < array.length - 1) || (array[index] > n); index++) {                //finds first index.
        if (array[index] == n) {
            foundIndex = index;
            break;
        }
    }
    if (foundIndex != -1) {
        for (int innerIndex = foundIndex; (innerIndex < array.length - 1)                            //finds last index.
                || (array[innerIndex] > n); innerIndex++) {
            if (array[innerIndex] == n) {
                array[array.length - 1] = innerIndex;
            }
        }
    }
    return (foundIndex);
    }
}

