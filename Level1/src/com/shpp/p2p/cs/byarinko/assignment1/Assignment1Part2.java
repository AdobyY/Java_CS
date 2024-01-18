package com.shpp.p2p.cs.byarinko.assignment1;

public class Assignment1Part2 extends SuperKarel {
    public void run() throws Exception {
        // Until the world ends, we build a wall every four steps
        while (frontIsClear()) {
            buildWall();
            for (int i = 0; i < 4; i++) move();
        }
        // Having reached the end, we build the last wall
        buildWall();
    }

    // Move north until we see a wall
    // and place the necessary beepers along the way
    private void buildWall() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }

        turnAround();
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }

        turnLeft();
    }
}


   