package com.shpp.p2p.cs.byarinko.assignment1;

public class Assignment1Part3 extends SuperKarel {
    public void run() throws Exception {

        // Clean the world of beepers
        pickUpAllBeepers();

        // Return to the initial position
        turnBack();

        // Put the beeper in the center of the map
        findCenter();
        putBeeper();


    }

    // Returns the Carl to his original position after clearing the world
    private void turnBack() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            move();
        }
        turnLeft();

    }

    // Finds the center of the map
    private void findCenter() throws Exception {

        // move to the upper left corner
        turnLeft();
        while (frontIsClear()) {
            move();
        }
        //make one movement to the left, and two right until we reach the end of the map
        turnAround();
        while (frontIsClear()) {
            turnLeft();
            move();
            turnRight();
            move();
            if (frontIsClear()) move();
        }
    }


    // Goes around each cell on the map and collects beepers
    private void pickUpAllBeepers() throws Exception {
        while (frontIsClear()) {

            cleanRowAndGoBack();

            // moves to a new line
            if (rightIsClear()) {
                turnRight();
                if (frontIsClear()) {
                    move();
                }

                turnRight();

            }
        }
    }

    // clears one line
    private void cleanRowAndGoBack() throws Exception {

        while (frontIsClear()) {
            if (beepersPresent()) {
                pickBeeper();
            }
            move();
        }

        turnAround();
        if (beepersPresent()) pickBeeper();

        while (frontIsClear()) {
            move();
        }
    }
}

