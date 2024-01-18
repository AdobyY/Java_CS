package com.shpp.cs.a.lectures.lec13;

public class SampleClicker {
    /* The value displayed on the counter. */
    private int value = 0;

    /* Two constructors exist - one for setting the initial
     * value, and one for using the default of 0.
     */
    public SampleClicker(int initialValue) {
        value = initialValue;
    }
    public SampleClicker() {
        // Do nothing
    }

    /* Methods for incrementing the counter or reading its value. */
    public void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }

    /* Human-readable string representation. */
    public String toString() {
        return "{" + value + "}";
    }

}
