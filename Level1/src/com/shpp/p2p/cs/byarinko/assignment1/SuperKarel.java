package com.shpp.p2p.cs.byarinko.assignment1;

import com.shpp.karel.KarelTheRobot;

public class SuperKarel extends KarelTheRobot{

    public void turnRight() throws Exception{
        for (int i = 0; i<3; i++){
            turnLeft();
        }
    }

    public void turnAround() throws Exception{
        turnLeft();
        turnLeft();
    }

}
