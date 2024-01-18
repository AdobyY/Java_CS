package com.shpp.p2p.cs.byarinko.assignment3;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

/*
The program makes an animation of a car moving on the road
 */
public class Assignment3Part6 extends WindowProgram {
    // Car properties
    private static final int WHEEL_RADIUS = 50;
    private static final int CAR_WIDTH = 260;
    private static final int CAR_HEIGHT = 80;
    private static final int ROOF_HEIGHT = 60;
    private static final int ROOF_WIDTH = CAR_WIDTH - 2 * WHEEL_RADIUS;
    private static final int ROAD_HEIGHT = 80;


    public void run() {
        waitForClick();
        makeSky();
        makeSun(180);
        makeRoad();

        int initialX = 100; // Initial x-coordinate of the car
        int y = getHeight() - CAR_HEIGHT - WHEEL_RADIUS;

        long startTime = System.currentTimeMillis();
        long animationDuration = 5000; // 5 seconds in milliseconds
        int frameCount = 0;

        while (frameCount < 50) { // Ensure at least 50 frames
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;

            if (elapsedTime >= animationDuration) {
                break; // Stop the animation after 5 seconds
            }

            int frameDuration = (int) (animationDuration / 50); // Calculate the frame duration

            removeAll(); // Remove all previous shapes from the canvas
            makeSky();
            makeSun(180);
            makeRoad();
            makeCar(initialX + frameCount * 5, y); // Draw the car at the new x-coordinate

            frameCount++;

            long sleepTime = frameDuration - elapsedTime % frameDuration;

            if (sleepTime > 0) {
                pause(sleepTime);
            }
        }
        exit();
    }

    // Draw blue sky
    private void makeSky() {
        GRect s = new GRect(0, 0, getWidth(), getHeight());
        s.setFilled(true);
        s.setColor(Color.cyan);
        add(s);
    }

    // Draw yellow sun
    private void makeSun(int size) {
        GOval o = new GOval(getWidth()-size,0, size, size);
        o.setFilled(true);
        o.setColor(Color.yellow);
        add(o);
    }

    // Draw gray road
    private void makeRoad() {
        GRect r = new GRect(0, getHeight()-ROAD_HEIGHT, getWidth(), ROAD_HEIGHT);
        r.setFilled(true);
        r.setColor(Color.darkGray);
        add(r);

    }

    // Draw the car in parts
    private void makeCar(int x, int y) {
        makeBody(x, y);
        makeWheels(x, y);
        makeHeadlight(x, y);
        makeRoof(x, y - ROOF_HEIGHT);
    }

    // Blue roof of the car
    private void makeRoof(int x, int y) {
        GRect r = new GRect(x + WHEEL_RADIUS, y, ROOF_WIDTH, ROOF_HEIGHT);
        r.setFilled(true);
        r.setColor(Color.blue);
        add(r);
    }

    // Red body of the car
    private void makeBody(int x, int y) {
        GRect b = new GRect(x, y, CAR_WIDTH, CAR_HEIGHT);
        b.setFilled(true);
        b.setColor(Color.red);
        add(b);
    }

    // Draw two wheels
    private void makeWheels(int x, int y) {
        // Draw first wheel
        GOval w1 = new GOval(x + WHEEL_RADIUS / 2,
                y + CAR_HEIGHT - WHEEL_RADIUS / 2,
                WHEEL_RADIUS, WHEEL_RADIUS);
        w1.setFilled(true);
        w1.setColor(Color.black);
        add(w1);

        // Draw second wheel
        GOval w2 = new GOval(x + CAR_WIDTH - WHEEL_RADIUS * 1.5,
                y + CAR_HEIGHT - WHEEL_RADIUS / 2,
                WHEEL_RADIUS, WHEEL_RADIUS);
        w2.setFilled(true);
        w2.setColor(Color.black);
        add(w2);
    }

    // Draw the yellow headlight
    private void makeHeadlight(int x, int y) {
        int headLightRadius = WHEEL_RADIUS / 3;

        GOval l = new GOval(x + CAR_WIDTH - headLightRadius / 2,
                y + CAR_HEIGHT / 2 - headLightRadius / 2,
                headLightRadius, headLightRadius);
        l.setFilled(true);
        l.setColor(Color.yellow);
        add(l);
    }
}


