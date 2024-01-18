package com.shpp.p2p.cs.byarinko.assignment1;

public class Assignment1Part4 extends SuperKarel {
    public void run() throws Exception {
        /*
        The condition checks if the world consists of only one column
         */
        if (frontIsBlocked()) {
            turnLeft();
            putBeepersFirstWay();
        } else { // otherwise, fills the first row and moves to the next ones
            putBeepersFirstWay();
            createChessboard();

        }
    }

    /*
    Creates a checkerboard of beepers starting from the second row until the world ends in the north.
     The end of the world is checked by leftIsClear() conditions || rightIsClear().
     Conditions (leftIsClear() && (notFacingNorth() || notFacingSouth())) to avoid looping
     */
    public void createChessboard() throws Exception {
        while ((leftIsClear() || rightIsClear()) && (leftIsClear() && (notFacingNorth() || notFacingSouth()))) {

            turnLeft();
            if (frontIsClear()) secondLine();

            turnRight();
            if (frontIsClear()) secondLine();

        }
    }

    /*
    Decides how to fill the next line, depending on the previous one.
     That is, if the previous line was filled by the putBeepersFirstWay() method, then
     the next one will be filled by the putBeepersSecondWay() method
     */
    public void secondLine() throws Exception {
        if (beepersPresent()) {
            move();
            if (leftIsBlocked()) {
                turnRight();
            } else turnLeft();

            putBeepersSecondWay();
        } else {
            move();
            if (leftIsBlocked()) {
                turnRight();
            } else turnLeft();

            putBeepersFirstWay();
        }
    }

    /*
    Puts beepers on the entire row through the cell, starting from the first cell
     */
    public void putBeepersFirstWay() throws Exception {
        while (frontIsClear()) {
            if (noBeepersPresent()) putBeeper();

            move();

            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
    }

    /*
    Puts beepers on the entire row through the cell, starting from the second cell
     */
    public void putBeepersSecondWay() throws Exception {
        while (frontIsClear()) {
            move();
            putBeeper();
            if (frontIsClear()) {
                move();

            }
        }
    }
}
