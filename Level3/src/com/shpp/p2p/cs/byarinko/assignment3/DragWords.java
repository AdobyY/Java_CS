package com.shpp.p2p.cs.byarinko.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DragWords extends WindowProgram {

    public void run() {
        addObjects();
        addMouseListeners();
    }

    /* The object which has been selected for dragging. */
    private GObject selectedObject = null;

    /* The mouse's last position. */
    private double lastX = 0, lastY = 0;

    /**
     * Selects the object under the mouse cursor when the mouse is pressed.
     * If nothing is found, that's okay - we'll set selectedObject to null.
     */
    public void mousePressed(MouseEvent e) {
        selectedObject = getElementAt(e.getX(), e.getY());
        lastX = e.getX();
        lastY = e.getY();
    }

    /**
     * Repositions the dragged object to the mouse's location when the mouse
     * is moved.
     */
    public void mouseDragged(MouseEvent e) {
		/* If there is something to drag at all, go move it. */
        if (selectedObject != null) {
            double dx = e.getX() - lastX;
            double dy = e.getY() - lastY;

            selectedObject.move( dx, dy);

            lastX = e.getX();
            lastY = e.getY();
        }
    }

    /**
     * Adds the "magnets" to the display.
     */
    private void addObjects() {
        try {
			/* Open the file for reading. */
            BufferedReader br = new BufferedReader(new FileReader("assets/words.txt"));

			/* Process the file by adding one magnet per line. */
            while (true) {
                String line = br.readLine();
                if (line == null) break;

                addMagnet(line);
            }

            br.close();
        } catch (IOException e) {
            println(":-(");
        }
    }

    /**
     * Adds a randomly-positioned "magnet" to the display with the given
     * label.
     *
     * @param text The text to use for the magnet.
     */
    private void addMagnet(String text) {
        RandomGenerator rgen = RandomGenerator.getInstance();

        GLabel label = new GLabel(text);
        label.setFont("DejaVuSerif-"+rgen.nextInt(20, 40));
        label.setColor(Color.BLUE);

		/* Randomly position the label. Due to the way that the label draws,
		 * we need to factor in the text ascent and descent when computing the
		 * y coordinate.
		 */
        double x = rgen.nextDouble(0, getWidth() - label.getWidth());
        double y = rgen.nextDouble(label.getAscent(), getHeight() - label.getDescent());

        label.setLocation(x, y);
        add(label);
    }
}