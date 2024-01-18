package com.shpp.p2p.cs.byarinko.assignment3;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class PhysicsAnimProgram extends WindowProgram {
    private static final double BALL_SIZE = 50;
    private static final double HORIZONTAL_VELOCITY = 1.0;
    private static final double GRAVITY = 0.425;
    private static final double ELASTICITY = 0.75;
    private static final double PAUSE_TIME = 1000.0 / 48;

    public void run() {
        GOval ball = makeBall();
        add(ball);
        bounceBall(ball);
    }

    private GOval makeBall() {
        GOval ball = new GOval(0, 0, BALL_SIZE, BALL_SIZE);
        ball.setFilled(true);
        ball.setColor(Color.BLUE);
        return ball;
    }

    private void bounceBall(GOval ball) {
        double dy = 0;
        while (true) {
            ball.move(HORIZONTAL_VELOCITY, dy);
            dy += GRAVITY;

            if (ballGelowFlor(ball) && dy > 0) {
                dy *= -ELASTICITY;
            }
            pause(PAUSE_TIME);
        }
    }

    private boolean ballGelowFlor(GOval ball) {
        return ball.getY() + ball.getHeight() >= getHeight();
    }

}
