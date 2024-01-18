package com.shpp.p2p.cs.byarinko.assignment3;


import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

public class ChaosGame  extends WindowProgram {

    public static final int DOTSCOUNT = 100000;

    public void run() {
        GPoint pt = new GPoint(0, 0);

		/* Repeatedly move toward a corner and plot a pixel. */
        while(true) {
            moveRandomly(pt);
            plotPixel(pt.getX(), pt.getY());
            pause(1);
        }
    }

    /* Chooses a random corner and moves halfway toward it. */
    private void moveRandomly(GPoint pt) {
        GPoint dest = chooseRandomCorner();

        double newX = (pt.getX() + dest.getX()) / 2.0;
        double newY = (pt.getY() + dest.getY()) / 2.0;

        pt.setLocation(newX, newY);
    }

    /* Chooses and returns one of the three corners of the window at random. */
    private GPoint chooseRandomCorner() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        int cornerIndex = rgen.nextInt(1, 3);

        if (cornerIndex == 1) {
            return new GPoint(0, 0);
        } else if (cornerIndex == 2) {
            return new GPoint(getWidth(), 0);
        } else {
            return new GPoint(0, getHeight());
        }
    }

    /* Draws a dot at the indicated location. */
    private void plotPixel(double x, double y) {
        GRect pixel = new GRect(x, y, 1, 1);
        pixel.setFilled(true);
        add(pixel);
    }
}

