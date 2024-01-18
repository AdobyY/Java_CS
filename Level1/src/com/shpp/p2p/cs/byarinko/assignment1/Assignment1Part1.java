package com.shpp.p2p.cs.byarinko.assignment1;

public class Assignment1Part1 extends SuperKarel {
    public void run() throws Exception{

        // go to the beeper
        goToTheBeeper();

        // pick up
        pickBeeper();

        // return to the starting position
        goToTheBeeper();

    }

    private void goToTheBeeper() throws Exception {
        move();
        move();
        turnRight();
        move();
        turnLeft();
        move();
        move();
        turnAround();
    }
}
