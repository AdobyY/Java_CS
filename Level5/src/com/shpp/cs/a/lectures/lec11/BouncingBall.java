package com.shpp.cs.a.lectures.lec11;
/*
 * File: BouncingBall.java
 * ========================================================
 * A program that simulates a bouncing ball.  This program
 * now allows the user to control the parameters of the
 * experiment.
 */
import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import javax.swing.*;

public class BouncingBall extends WindowProgram {
    /* The initial X velocity of the ball. */
    private static final double INITIAL_SPEED = 5.0;

    /* How long to delay between frames. */
    private static final double PAUSE_TIME = 1000.0 / 75;

    /* The size of the ball. */
    private static final double BALL_SIZE = 50;

    /* Sliders for gravity and elasticity. */
    private JSlider gravity, elasticity;

    public void init() {
		/* Set up the gravity slider. */
        add(new JLabel("Gravity: "), SOUTH);
        gravity = new JSlider(0, 100, 25);
        add(gravity, SOUTH);

		/* Set up the elasticity slider. */
        add(new JLabel("Elasticity: "), SOUTH);
        elasticity = new JSlider(0, 100, 75);
        add(elasticity, SOUTH);

		/* Add in a title. */
        add(new JLabel("Follow the bouncing ball!"), NORTH);
    }

    /* Simulates a bouncing ball. */
    public void run() {
        while (true) {
            GOval ball = createBall();
            add(ball);
            bounceBall(ball);
            removeAll();
        }
    }

    /**
     * Creates the ball that will be dropped.
     *
     * @return The ball that will be dropped.
     */
    private GOval createBall() {
        GOval ball = new GOval(0, 0, BALL_SIZE, BALL_SIZE);
        ball.setFilled(true);
        ball.setColor(Color.BLUE);
        return ball;
    }

    /**
     * Returns the currently-selected gravity.
     *
     * @return The current gravity setting.
     */
    private double getGravity() {
        return gravity.getValue() / 100.0;
    }

    /**
     * Returns the currently-selected elasticity.
     *
     * @return The current elasticity setting.
     */
    private double getElasticity() {
        return elasticity.getValue() / 100.0;
    }

    /**
     * Simulates the ball dropping.
     *
     * @param ball The ball to drop.
     */
    private void bounceBall(GOval ball) {
		/* Track the ball's velocity. */
        double dx = INITIAL_SPEED;
        double dy = 0;

		/* Until the ball hits the ground, keep simulating the ball falling. */
        while (isBallOnScreen(ball)) {
            ball.move(dx, dy);

			/* Use the sliders to determine gravity or elasticity. */
            if (isBallAboveGround(ball)) {
                dy += getGravity();
            } else {
                dy *= -getElasticity();
                moveBallOutOfGround(ball);
            }

            pause(PAUSE_TIME);
        }
    }

    /**
     * Returns whether the given ball is on the screen.
     *
     * @param ball The ball to check.
     * @return Whether it's on-screen.
     */
    private boolean isBallOnScreen(GOval ball) {
        return ball.getX() < getWidth();
    }

    /**
     * Moves the ball out of the ground.
     *
     * @param ball The ball to move out of the ground.
     */
    private void moveBallOutOfGround(GOval ball) {
        ball.move(0, getHeight() - (ball.getY() + ball.getHeight()));
    }

    /**
     * Returns whether the ball is above ground.
     *
     * @param ball The ball to check.
     * @return Whether it's above ground.
     */
    private boolean isBallAboveGround(GOval ball) {
        return ball.getY() + ball.getHeight() < getHeight();
    }
}
