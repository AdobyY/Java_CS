package com.shpp.p2p.cs.byarinko.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    // The radius of one circle and their number
    private static final int CIRCLE_RADIUS = 50;
    private static final int NUM_OF_CIRCLES = 10;

    public void run() {
        makeCaterpillar(NUM_OF_CIRCLES);
    }

    /*
    Creates a caterpillar of length num using the method makeCircle
     */
    private void makeCaterpillar(int num) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < num; i++) {
            makeCircle(x, y);

            // Increases the coordinate x
            if (i % 2 == 1) {
                x += CIRCLE_RADIUS / 2;
                y = 0;
            } else {
                x += CIRCLE_RADIUS / 2;
                y = CIRCLE_RADIUS / 2;
            }
        }
    }

    /*
    Draws one circle
     */
    private void makeCircle(int x, int y) {
        GOval circle = new GOval(x, y, CIRCLE_RADIUS, CIRCLE_RADIUS);
        circle.setFilled(true);
        circle.setFillColor(Color.green);
        circle.setColor(Color.red);
        add(circle);
    }
}
