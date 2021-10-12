/**
 * Counter class. Deals with increasing or decreasing a value.
 */
public class Counter {
    private int value = 0;
    /**
     * increase the value of counter.
     * @param number increase by X.
     */
    void increase(int number) {
        value += number;
    }

    /**
     * decrease the value of counter.
     * @param number subtract by X.
     */
    void decrease(int number) {
        value -= number;
    }

    /**
     * get counter value.
     * @return current value.
     */
    int getValue() {
        return value;
    }
}