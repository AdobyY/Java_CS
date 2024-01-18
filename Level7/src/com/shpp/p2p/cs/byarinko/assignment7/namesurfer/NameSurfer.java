package com.shpp.p2p.cs.byarinko.assignment7.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private NameSurferGraph graph;
    private NameSurferDataBase data;
    private JTextField nameField;


	/* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        data = new NameSurferDataBase(NAMES_DATA_FILE);
        graph = new NameSurferGraph();
        add(graph);
        createButtons();
        addActionListeners();
    }

    private void createButtons() {
        add(new JLabel("Name"), NORTH);

        nameField = new JTextField(20);
        nameField.setActionCommand("Graph");
        nameField.addActionListener(this);
        add(nameField, NORTH);

        add(new JButton("Graph"), NORTH);
        add(new JButton("Clear"), NORTH);
    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Graph")) {
            String name = nameField.getText();
            NameSurferEntry entry = data.findEntry(name.toLowerCase());
            if (entry != null) {
                graph.addEntry(entry);
                graph.update();
            }
        } else if (e.getActionCommand().equals("Clear")) {
            graph.clear();
        }
    }
}
