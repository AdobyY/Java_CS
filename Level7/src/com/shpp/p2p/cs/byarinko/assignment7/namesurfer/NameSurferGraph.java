package com.shpp.p2p.cs.byarinko.assignment7.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import static java.lang.Character.toUpperCase;


public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private final ArrayList<NameSurferEntry> entries = new ArrayList<>();
    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);

    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entries.clear();
        update();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entries.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application is supposed to call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes, see componentResized
     */
    public void update() {
        removeAll(); // Clear the canvas
        drawBackground(); // Redraw the background
        drawNames(); // Draw names
    }

    /**
     * Draws names that have been entered
     */
    private void drawNames() {
        for (int i = 0; i < entries.size(); i++) {
            add(createSurfer(entries.get(i), i));
        }
    }

    private GCompound createSurfer(NameSurferEntry entry, int colorCount) {
        GCompound surfCompound = new GCompound();
        surfCompound.add(drawLabels(entry, colorCount % 4));
        surfCompound.add(drawLines(entry, colorCount % 4));
        return surfCompound;
    }

    /**
     * Generates labels for each decade with corresponding names and rank labels
     * @param entry Contains Data about the popularity of a specific name over the decades
     * @param colorCount Select specific color
     * @return Returns the set of labels for a specific NameSurferEntry in the graph
     */
    private GCompound drawLabels(NameSurferEntry entry, int colorCount) {
        GCompound labels = new GCompound();
        int div = getWidth() / NDECADES;
        double startX = 0;

        for (int i = 0; i < NDECADES; i++) {
            double yIncrements = (getHeight() - (GRAPH_MARGIN_SIZE * 2.0)) / MAX_RANK;
            double y = (entry.getRank(i) * yIncrements) + GRAPH_MARGIN_SIZE;
            String name = entry.getName();

            if (entry.getRank(i) == 0) {
                y = getHeight() - GRAPH_MARGIN_SIZE;
                name += "*";
            } else {
                name += " " + entry.getRank(i);
            }
            GLabel surfLabel = new GLabel(toUpperCase(name.charAt(0))+name.substring(1), startX, y);

            // Set color and font
            surfLabel.setColor(getColor(colorCount));
            surfLabel.setFont("Arial-12");
            labels.add(surfLabel);
            startX += div;
        }
        return labels;
    }

    /**
     * Creates and returns a group of graph line objects to display the popularity of a name
     * over the years. Each line indicates the name's rank change over the decades.
     * @param entry Contains Data about the popularity of a specific name over the decades
     * @param colorCount Select specific color
     * @return Graph for entry
     */
    private GCompound drawLines(NameSurferEntry entry, int colorCount) {
        GCompound lines = new GCompound();
        int div = getWidth() / NDECADES;
        double startX = 0;

        for (int i = 0; i < NDECADES - 1; i++) {
            double yIncrements = (getHeight() - (GRAPH_MARGIN_SIZE * 2.0)) / MAX_RANK;
            double y = (entry.getRank(i) * yIncrements) + GRAPH_MARGIN_SIZE;

            if (entry.getRank(i) == 0) {
                y = getHeight() - GRAPH_MARGIN_SIZE;
            }

            double nextY;
            if (entry.getRank(i + 1) == 0) {
                nextY = getHeight() - GRAPH_MARGIN_SIZE;
            } else {
                nextY = (entry.getRank(i + 1) * yIncrements) + GRAPH_MARGIN_SIZE;
            }

            GLine surfLine = new GLine(startX, y, startX + div, nextY);
            surfLine.setColor(getColor(colorCount));
            
            lines.add(surfLine);
            startX += div;
        }
        return lines;
    }

    /**
     * Returns color depending on colorCount param
     * @param colorCount The number corresponding to the color
     * @return color
     */
    private Color getColor(int colorCount) {
        return switch (colorCount) {
            case 0 -> Color.blue;
            case 1 -> Color.red;
            case 2 -> Color.magenta;
            default -> Color.black;
        };
    }

    /**
     * Draws a grid on a graph (vertical and horizontal lines)
     */
    private void drawBackground() {
        // Draw vertical decade lines
        for (int i = 0; i < NDECADES; i++) {
            double x = i * ((double) getWidth() / NDECADES);
            add(new GLine(x, 0, x, getHeight()));
            int decade = START_DECADE + i * 10;
            add(new GLabel(Integer.toString(decade), x, getHeight() - 2));
        }

        // Draw horizontal lines
        double y = GRAPH_MARGIN_SIZE;
        add(new GLine(0, y, getWidth(), y));
        y = getHeight() - GRAPH_MARGIN_SIZE;
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), y));
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {}

    public void componentMoved(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {}
}
