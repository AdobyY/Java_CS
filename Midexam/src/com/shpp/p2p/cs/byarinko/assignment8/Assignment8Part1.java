package com.shpp.p2p.cs.byarinko.assignment8;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Assignment8Part1 extends GraphicsProgram {
    // Array of squares
    private GRect[] squares;
    // Number of squares
    private int NUM_SQUARES = 30;
    // Move away or move closer
    private boolean CHASE_MODE = true;
    // The minimum distance the squares will move
    private int  DISTANCE = 20;

    public void run() {
        addMouseListeners();
        createSquares();

        while (true) {
            // Get the X and Y coordinates of the cursor
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            int x = (int) mouseLocation.getX();
            int y = (int) mouseLocation.getY();

            // Move the squares
            moveSquares(x, y);

            // Pause for 20 milliseconds
            pause(20);
        }
    }

    /**
     * Creates squares and adds them to
     */
    private void createSquares() {
        squares = new GRect[NUM_SQUARES];
        for (int i = 0; i < NUM_SQUARES; i++) {
            GRect square = new GRect(20, 20);
            square.setFilled(true);
            add(square, Math.random() * getWidth(), Math.random() * getHeight());
            squares[i] = square;
        }
    }

    /**
     * makes the squares move
     * @param x x coordinate of the mouse
     * @param y y coordinate of the mouse
     */
    private void moveSquares(int x, int y) {
        double mouseX = x;
        double mouseY = y;

        for (int i = 0; i < NUM_SQUARES; i++) {
            GRect square = squares[i];
            double dx;
            double dy;

            if (Math.abs(mouseX - square.getX()) < DISTANCE || Math.abs(mouseY - square.getY()) < DISTANCE) {
                if (CHASE_MODE) {
                    dx = mouseX - square.getX();
                    dy = mouseY - square.getY();
                } else {
                    dx = square.getX() - mouseX;
                    dy = square.getY() - mouseY;
                }

                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance > 0) {
                    dx = dx / distance;
                    dy = dy / distance;
                }

                square.move(dx, dy);
            }

        }
    }

    public void mousePressed(MouseEvent e) {
        CHASE_MODE = !CHASE_MODE;
    }

    public static void main(String[] args) {
        new Assignment8Part1().start();
    }
}

