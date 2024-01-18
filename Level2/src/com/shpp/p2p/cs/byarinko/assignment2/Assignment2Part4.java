package com.shpp.p2p.cs.byarinko.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    // Set Height and Width of the Flag
    private final int FLAG_HEIGHT = 200;
    private final int FLAG_WIDTH = 300;

    public void run() {
        makeFlag(Color.black, Color.red, Color.yellow);

        makeLabel("Flag of Germany");
    }

    /*
        Makes an inscription in the lower right corner
     */
    private void makeLabel(String s) {
        GLabel l = new GLabel(s);
        l.setFont("TimesRoman-36");

        double x = getWidth() - l.getWidth();
        double y = getHeight();
        add(l, x, y - l.getDescent());
    }

    /*
         Makes Flag with three colors
     */
    private void makeFlag(Color c1, Color c2, Color c3) {
        createRect(c1, 1);
        createRect(c2, 2);
        createRect(c3, 3);
    }


    /*
    Draws the first, second, or third row of the flag, depending on the parameter passed
     */
    private void createRect(Color color, int position) {
        // Creates rectangle
        GRect rect = new GRect(FLAG_WIDTH, FLAG_HEIGHT / 3);
        rect.setFilled(true);
        rect.setFillColor(color);
        rect.setColor(color);

        double x, y;

        // Check in which position the rectangle
        if (position == 1) { // Above the central one
            x = (getWidth() - rect.getWidth()) / 2;
            y = ((getHeight() - rect.getHeight()) / 2) - (FLAG_HEIGHT / 3);
        } else if (position == 2) { // Middle of the center
            x = (getWidth() - rect.getWidth()) / 2;
            y = (getHeight() - rect.getHeight()) / 2;
        } else { // Below the central one
            x = (getWidth() - rect.getWidth()) / 2;
            y = ((getHeight() - rect.getHeight()) / 2) + (FLAG_HEIGHT / 3);
        }

        add(rect, x, y);

    }
}
