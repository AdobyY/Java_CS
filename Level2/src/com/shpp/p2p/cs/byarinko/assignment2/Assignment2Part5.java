package com.shpp.p2p.cs.byarinko.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    public void run() {
        makeBoxes(NUM_COLS, NUM_ROWS);
    }

    /*
    Makes a matrix of black boxes using the makeRowOfBoxes method, which makes a single row of boxes
     */
    private void makeBoxes(int numCols, int numRows) {
        // Defines the y coordinate for each row of boxes
        int y = (getHeight() - ((int) (BOX_SIZE * NUM_ROWS + BOX_SPACING * (NUM_ROWS - 1)))) / 2;

        for (int i = 0; i < numRows; i++) {
            makeRowOfBoxes(numCols, y);
            y += BOX_SIZE + BOX_SPACING;
        }

    }

    /*
        Makes a single row of boxes
    */
    private void makeRowOfBoxes(int numCols, int y) {
        // Defines the x coordinate for each box
        int x = (getWidth() - ((int) (BOX_SIZE * NUM_COLS + BOX_SPACING * (NUM_COLS - 1)))) / 2;

        // Fill the row with boxes
        for (int i = 0; i < numCols; i++) {
            makeBox(x, y);
            x += BOX_SIZE + BOX_SPACING;
        }
    }

    /*
    Makes one box according to the given constants
     */
    private void makeBox(int x, int y) {
        GRect box = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        box.setFilled(true);
        box.setColor(Color.black);
        box.setFillColor(Color.black);
        add(box);
    }
}
