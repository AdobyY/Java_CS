package com.shpp.p2p.cs.byarinko.assignment2;


import acm.graphics.*;
import java.awt.*;
import com.shpp.cs.a.graphics.WindowProgram;

public class Robot extends WindowProgram {

    /* Constants controlling the size of the robot head. */
    private static final double HEAD_WIDTH = 150;
    private static final double HEAD_HEIGHT = 250;

    /* The radius of each eye. */
    private static final double EYE_RADIUS = 10;

    /* The width and height of the mouth. */
    private static final double MOUTH_WIDTH = 60;
    private static final double MOUTH_HEIGHT = 20;

    public void run() {
        /* Determine the coordinates of the center of the window. */
        double xCenter = getWidth() / 2.0;
        double yCenter = getHeight() / 2.0;

        /* Draw each piece. */
        addHead(xCenter - HEAD_WIDTH / 2, yCenter - HEAD_HEIGHT / 2);
        addEye(xCenter - HEAD_WIDTH / 4, yCenter - HEAD_HEIGHT / 4);
        addEye(xCenter + HEAD_WIDTH / 4, yCenter - HEAD_HEIGHT / 4);
        addMouth(xCenter - MOUTH_WIDTH / 2, yCenter + HEAD_HEIGHT / 4);
    }

    /**
     * Draws the robot head.
     *
     * @param x The x coordinate of the upper-left corner of the robot head.
     * @param y The y coordinate of the upper-left corner of the robot head.
     */
    private void addHead(double x, double y) {
        drawRectangle(x, y, HEAD_WIDTH, HEAD_HEIGHT, Color.GRAY);
    }

    /**
     * Draws a rectangle with the indicated properties.
     *
     * @param x The x coordinate of the upper-left corner of the rectangle.
     * @param y The y coordinate of the upper-left corner of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color to use for the rectangle.
     */
    private void drawRectangle(double x, double y, double width, double height,	Color color) {
        GRect rect = new GRect(x, y, width, height);
        rect.setFilled(true);
        rect.setColor(color);
        add(rect);
    }

    /**
     * Draws a circle centered at the indicated location.
     *
     * @param xCenter The x coordinate of the center of the circle.
     * @param yCenter The y coordinate of the center of the circle.
     * @param radius The radius of the circle.
     * @param color The color of the circle.
     */
    private void drawCenteredCircle(double xCenter, double yCenter,
                                    double radius, Color color) {
        /* Compute the coordinates of the upper-left corner of the circle,
         * which we need when we create the oval, from the center and radius.
         */
        double x = xCenter - radius;
        double y = yCenter - radius;
        GOval circle = new GOval(x, y, 2 * radius, 2 * radius);
        circle.setColor(color);
        circle.setFilled(true);
        add(circle);
    }

    /**
     * Draws an eye centered on the indicated position.
     *
     * @param xCenter The x coordinate of the center of the eye.
     * @param yCenter The y coordinate of the center of the eye.
     */
    private void addEye(double cx, double cy) {
        drawCenteredCircle(cx, cy, EYE_RADIUS, Color.YELLOW);
    }

    /**
     * Draws the robot mouth at the indicated position.
     *
     * @param x The x coordinate of the upper-left corner of the mouth.
     * @param y The y coordinate of the upper-left corner of the mouth.
     */
    private void addMouth(double x, double y) {
        drawRectangle(x,y, MOUTH_WIDTH, MOUTH_HEIGHT, Color.WHITE);
    }
}
