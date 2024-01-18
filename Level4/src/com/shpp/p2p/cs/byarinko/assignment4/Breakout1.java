package com.shpp.p2p.cs.byarinko.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout1 extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Calculate brick width from APPLICATION_WIDTH
     */
    private static final int BRICK_WIDTH =
            (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static int NTURNS = 3;

    /**
     * Create paddle object and ints X coordinate
     */
    private GRect paddle;
    private double paddleX;
    /**
     * Create ball object and its speed
     */
    private GOval ball;
    private double vx, vy;

    public void run() {
        // Mouse event handler
        addMouseListeners();

        // Play game until all lives are used up
        playGame();
    }

    /**
     * Method starts the round by checking if the player still has lives
     */
    private void playGame() {
        while (NTURNS > 0) {
            startRound();
            createHearts(NTURNS);
            waitForClick();

            while (checkEvent()) {
                createHearts(NTURNS);
                moveBall();
                checkForCollision();

                pause(10); // Delay for animation
            }
        }
    }

    /**
     * Check if there is at least one more brick
     * Show message if ball fell and game over
     * @return false if all bricks are brake or NTURNS = 0 and true otherwise
     */
    private boolean checkEvent(){
        if (allBricksCleared()) {
            // All bricks are brake
            showMessage("You win!");
            waitForClick();
            return false;
        } else if (ball.getY() >= getHeight()) {
            // The ball fell
            NTURNS--;

            if (NTURNS > 0) {
                showMessage("You have " + NTURNS + " tries left.");
                waitForClick();
                startRound();
            } else {
                showMessage("Game over! You lose.");
                return false;
            }
        }
        return true;
    }

    /**
     * Creates hearts in upper left corner
     */
    private void createHearts(int n) {
        double heartWidth = 20;

        for (int i = 0; i < n; i++) {
            // Add left circle of heart
            GOval o1 = new GOval(i*heartWidth, 0, heartWidth / 2, heartWidth / 2);
            o1.setFilled(true);
            o1.setColor(Color.red);
            add(o1);

            // Add right circle of heart
            GOval o2 = new GOval(heartWidth / 2+ i*heartWidth, 0, heartWidth / 2, heartWidth / 2);
            o2.setFilled(true);
            o2.setColor(Color.red);
            add(o2);

            // Add bottom triangle of heart
            GPolygon r = new GPolygon();
            r.addVertex(0 + i*heartWidth, heartWidth / 4);
            r.addVertex(heartWidth + i*heartWidth, heartWidth / 4);
            r.addVertex(heartWidth / 2 + i*heartWidth, heartWidth);
            r.setFilled(true);
            r.setColor(Color.red);
            add(r);
        }
    }

    /**
     * Starts the round
     */
    private void startRound() {
        removeAll(); // Clear the screen before starting a new round
        createBall();
        createPaddle();
        initializeBallVelocity();
        drawBrickWall();
    }

    /**
     * Check if all bricks are cleared
     */
    private boolean allBricksCleared() {
        int brickCount = 0;
        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int col = 0; col < NBRICKS_PER_ROW; col++) {
                if (getElementAtBrick(row, col) != null) {
                    brickCount++;
                }
            }
        }
        return brickCount == 0;
    }

    /**
     * Gets the object in the specified column and row in the brick array
     */
    private GObject getElementAtBrick(int row, int col) {
        double x = BRICK_SEP / 2 + col * (BRICK_WIDTH + BRICK_SEP);
        double y = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);
        return getElementAt(x, y);
    }

    /**
     * Shows a message after the ball is dropped
     */
    private void showMessage(String message) {
        GLabel label = new GLabel(message, getWidth() / 2, getHeight() / 2);
        label.setFont("Arial-24");
        label.move(-label.getWidth() / 2, -label.getHeight() / 2);
        add(label);
    }

    /**
     * Create a paddle
     */
    private void createPaddle() {
        paddle = new GRect(0, getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setColor(Color.black);
        add(paddle);
        paddleX = (getWidth() - PADDLE_WIDTH) / 2;
        paddle.setLocation(paddleX, getHeight() - PADDLE_Y_OFFSET);
    }

    /**
     * Method is called when the user moves the mouse on the screen,
     * it is designed to control the movement of the racket in the game
     */
    public void mouseMoved(MouseEvent e) {
        double mouseX = e.getX();
        // Center the racket relative to the position of the mouse along the X axis
        paddleX = mouseX - PADDLE_WIDTH / 2;
        // A restriction so that the racket does not go beyond the screen
        if (paddleX < 0) {
            paddleX = 0;
        } else if (paddleX + PADDLE_WIDTH > getWidth()) {
            paddleX = getWidth() - PADDLE_WIDTH;
        }
        paddle.setLocation(paddleX, getHeight() - PADDLE_Y_OFFSET);
    }

    /**
     * Create a ball
     */
    private void createBall() {
        ball = new GOval(getWidth() / 2 - BALL_RADIUS,
                getHeight() / 2 - BALL_RADIUS,
                2 * BALL_RADIUS,
                2 * BALL_RADIUS);
        ball.setFilled(true);
        add(ball);
    }

    /**
     * Initialization of the initial speed of the ball in the game
     */
    private void initializeBallVelocity() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vy = 3.0; // The ball moves dovn
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx; // Change the direction of movement randomly
        }
    }

    /**
     * Moves the ball
     */
    private void moveBall() {
        ball.move(vx, vy);
    }

    /**
     * Method is responsible for the physics of ball movement
     * and processing of all possible collisions between the ball and game objects,
     * as well as game walls.
     */
    private void checkForCollision() {
        double ballX = ball.getX();
        double ballY = ball.getY();

        GObject collider = getCollidingObject(ballX, ballY);

        if (collider != null) {
            if (collider == paddle) {
                vy = -vy; // Bounce from the racket
            } else {
                remove(collider); // Delete brick
                vy = -vy; // We change the direction of movement after bouncing off the brick
            }
        }

        if (ballX <= 0 || ballX >= getWidth() - 2 * BALL_RADIUS) {
            vx = -vx; // Bounce from the left or right wall
        }

        if (ballY <= 0) {
            vy = -vy; // Bounce from the upper wall
        }

        if (ball.getBounds().intersects(paddle.getBounds())) {
            vy = -vy; // Bounce from the paddle
        }

    }

    /**
     * The method is designed to determine the object
     * with which the ball collided at specific coordinates
     */
    private GObject getCollidingObject(double x, double y) {
        if (getElementAt(x, y) != null) {
            return getElementAt(x, y);
        } else if (getElementAt(x + 2 * BALL_RADIUS, y) != null) {
            return getElementAt(x + 2 * BALL_RADIUS, y);
        } else if (getElementAt(x, y + 2 * BALL_RADIUS) != null) {
            return getElementAt(x, y + 2 * BALL_RADIUS);
        } else if (getElementAt(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS) != null) {
            return getElementAt(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS);
        } else {
            return null;
        }
    }

    /**
     * Draws a brick wall
     */
    private void drawBrickWall() {
        double startX = (getWidth() - (NBRICKS_PER_ROW * (BRICK_WIDTH + BRICK_SEP) - BRICK_SEP)) / 2;
        double startY = BRICK_Y_OFFSET;

        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int col = 0; col < NBRICKS_PER_ROW; col++) {
                makeBrick(startX, startY, row, col);
            }
        }

    }

    /**
     * Draw one brick
     * @param startX - x coordinate
     * @param startY - y coordinate
     * @param row - the line in which the brick is drawn
     * @param col - the column in which the brick is drawn
     */
    private void makeBrick(double startX, double startY, int row, int col) {
        // Array of colors
        Color[] brickColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

        GRect brick = new GRect(startX + col * (BRICK_WIDTH + BRICK_SEP),
                startY + row * (BRICK_HEIGHT + BRICK_SEP),
                BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(brickColors[row / 2]); // Change brick color
        add(brick);
    }
}
