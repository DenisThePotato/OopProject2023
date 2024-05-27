//207270621 Denis Mogilevsky

/**
 * @author Denis Mogilevsky.
 * a class representing a simple counter.
 */
public class Counter {
    private int value = 0;

    /**
     * add number to current count.
     * @param number to be added.
     */
    void increase(int number) {
        value += number;
    }

    /**
     * subtract number from current count.
     * @param number to be subtracted.
     */
    void decrease(int number) {
        value -= number;
    }

    /**
     * getter.
     * @return counter value.
     */
    int getValue() {
        return value;
    }
}
