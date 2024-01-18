package com.shpp.p2p.cs.byarinko.assignment3;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

public class Main extends WindowProgram{

    public void run(){
        GOval o = new GOval(0, 0, 20, 20);
        add(o);
        while (true){
            o.move(1, 1);
            pause(10);
        }
    }
}