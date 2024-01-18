package com.shpp.p2p.cs.byarinko.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part4 extends WindowProgram {
    // Width, Height and number of bricks
    private static final int BRICK_HEIGHT = 20;
    private static final int BRICK_WIDTH = 40;
    private static final int BRICKS_IN_BASE = 13;

    @Override
    public void run() {
        makePyramid();
    }

    // Make whole pyramid of bricks
    private void makePyramid() {
        int numOfBricks = BRICKS_IN_BASE;
        int relativeY = getHeight() - BRICK_HEIGHT;
        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            makeRowOfBricks(relativeY, numOfBricks);
            relativeY -= BRICK_HEIGHT;
            numOfBricks -= 1;
        }
    }

    // Make one row of bricks
    private void makeRowOfBricks(int y, int numOfBricks) {
        int relativeX = (getWidth() - BRICK_WIDTH * numOfBricks) / 2;
        for (int i = 0; i < numOfBricks; i++) {
            makeBrick(relativeX, y);
            relativeX += BRICK_WIDTH;
        }
    }

    // Make one brick with the given parameters
    private void makeBrick(int x, int y) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(Color.gray);
        brick.setFillColor(Color.yellow);
        add(brick);
    }
}
