package com.shpp.cs.a.lectures.lec12;


/*
 * File: Keyboard.java
 * ======================================================================
 * A program that displays a keyboard that the user can play!
 */

import java.awt.event.*;
import java.util.*;
import java.io.*;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;
import com.shpp.cs.a.lectures.lec10.StdAudio;

public class Keyboard extends WindowProgram {
    /* A nice window size. */
    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 235;

    /* The file containing the keyboard layout. */
    private static final String KEYBOARD_FILE = "assets/piano/keyboard/keyboard.txt";

    public void run() {
        addKeyboard();
        addMouseListeners();
    }

    /* A map from GObjects (the keys) to sound clips to play. */
    private HashMap<GObject, double[]> soundMap = new HashMap<GObject, double[]>();

    /**
     * Reacts to a mouse press. If the mouse is pressed on a key for which
     * we have a sound clip, that sound clip is played.
     */
    public void mousePressed(MouseEvent e) {
        GObject hit = getElementAt(e.getX(), e.getY());
        if (soundMap.containsKey(hit)) {
            StdAudio.play(soundMap.get(hit));
        }
    }

    /**
     * Reads the keyboard from the keyboard file, adding the keys and setting
     * up the sound map.
     */
    private void addKeyboard() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(KEYBOARD_FILE));

            while (true) {
				/* Read this entry, stopping if no more data remains. */
                String note = br.readLine();
                String x = br.readLine();
                String y = br.readLine();
                String width = br.readLine();
                String height = br.readLine();
                String isWhite = br.readLine();

                if (isWhite == null)
                    break;

				/* Create the key from the data. */
                GRect key = new GRect(
                        Double.parseDouble(x),
                        Double.parseDouble(y),
                        Double.parseDouble(width),
                        Double.parseDouble(height));
                if (isWhite.equals("false")) {
                    key.setFilled(true);
                }

                add(key);

				/* Associate this key with the sound clip to play. */
                soundMap.put(key, StdAudio.read("assets/piano/keyboard/"+note));
            }

            br.close();
        } catch (IOException e) {
            println(":-(");
        }
    }
}
