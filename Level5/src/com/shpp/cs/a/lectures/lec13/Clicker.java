package com.shpp.cs.a.lectures.lec13;


public class Clicker implements IClicker{

    private String password = "bigpassword";
    private int realNumberOfClicks = 0;

    // overloading constructors
    public Clicker(int initialNumOfClicks) {
        numOfClicks = initialNumOfClicks;
    }
    public Clicker() {
        numOfClicks = 0;
    }

    private int numOfClicks;

    public void click() {
        numOfClicks++;
        realNumberOfClicks++;
    }
    public void click(int x) {
        numOfClicks += x;
    }

    public int getClickCount() {
        if (numOfClicks < 0)
            return 0;
        return numOfClicks;
    }

    public void copyDataFromAnotherClicker(Clicker x) {
        numOfClicks = x.numOfClicks;
        realNumberOfClicks = x.realNumberOfClicks;
        password = x.password;
    }

    @Override
    public String toString() {
        return "clicker<"+numOfClicks+">";
    }
}
