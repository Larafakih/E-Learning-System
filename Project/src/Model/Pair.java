package Model;

import UI.*;
import javax.swing.*;

/**
 *
 * @author Lara
 */
public class Pair<T, S, R, P> {

    private T first;
    private S second;
    private R third;
    private P fourth;

    /**
     * Constructs a pair containing two given elements, where first is a JLabel.
     */
    public Pair(T firstElement, S secondElement, R thirdElement, P fourthElement) {
        first = firstElement;
        second = secondElement;
        third = thirdElement;
        fourth = fourthElement;
    }

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
    }

    public T getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public R getThird() {
        return third;
    }

    public P getFourth() {
        return fourth;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}
