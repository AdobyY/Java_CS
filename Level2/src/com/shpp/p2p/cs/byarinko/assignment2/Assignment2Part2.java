package com.shpp.p2p.cs.byarinko.assignment2;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 500;
    // Choose the smallest size of the window so that the circles fit in the corner
    private int CIRCLE_RADIUS = Math.min(APPLICATION_HEIGHT, APPLICATION_WIDTH) / 3;

    public void run() {
        // Draw circles
        drawCircle(0, 0); // upper left
        drawCircle(getWidth() - CIRCLE_RADIUS, 0); // upper right
        drawCircle(0, getHeight() - CIRCLE_RADIUS); // lower left
        drawCircle(getWidth() - CIRCLE_RADIUS, getHeight() - CIRCLE_RADIUS); // lower right

        // Draw Rectangle
        drawRectangle();
    }

    /*
    Method that draws white rectangle in the middle
     */
    private void drawRectangle() {
        GRect rect = new GRect(CIRCLE_RADIUS / 2, CIRCLE_RADIUS / 2,
                getWidth() - CIRCLE_RADIUS,
                getHeight() - CIRCLE_RADIUS);
        rect.setColor(Color.white);
        rect.setFilled(true);
        rect.setFillColor(Color.white);
        add(rect);
    }

    /*
    Methods that draws black circle
*/
    private void drawCircle(double x, double y) {
        GOval circle = new GOval(x, y, CIRCLE_RADIUS, CIRCLE_RADIUS);
        circle.setFilled(true);
        circle.setFillColor(Color.black);
        add(circle);

    }

}
