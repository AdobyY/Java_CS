package com.shpp.cs.a.lectures.lec13;

import acm.graphics.GObject;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;

public class MyTestProgram extends WindowProgram {

    public static class SuperClicker extends GOval implements IClicker {

        private int numOfClicks = 0;
        public SuperClicker(int w, int h) {
            this(w,h,0);
        }
        public SuperClicker(int w, int h, int initialClicksCount) {
            this(0,0,w,h,initialClicksCount);
        }
        public SuperClicker(int x, int y, int w, int h) {
            this(x,y,w,h, 0);
        }
        public SuperClicker(int x, int y, int w, int h, int initialClicksCount) {
            super(x,y,w,h);
            numOfClicks = initialClicksCount;
        }


        public void click() {
            numOfClicks++;
            setLocation(getX()-1, getY()-1);
            setSize(getWidth() + 2, getHeight() + 2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        GObject e = getElementAt(mouseEvent.getX(), mouseEvent.getY());
        if (e == c) {
            c.click();
        }
    }

    SuperClicker c = null;

    @Override
    public void init() {
    }

    @Override
    public void run() {
        c = new SuperClicker(100,50,1000);
        add(c);
        addMouseListeners();
    }


}
