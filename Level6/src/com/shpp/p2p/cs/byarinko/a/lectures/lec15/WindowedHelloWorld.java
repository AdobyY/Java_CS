package com.shpp.p2p.cs.byarinko.a.lectures.lec15;

import javax.swing.*;

public class WindowedHelloWorld {

	public static void main(String[] args) {
		/* Create a new window. */
		JFrame frame = new JFrame();
		
		/* Set up the window to have the right size and title. */
		frame.setSize(400, 400);
		frame.setTitle("Hello, world!");
		
		/* Make sure the program exits when we close the window. */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Install a component in the window. */
        frame.add(new BlueCircleComponent());

		/* Make everything visible. */
		frame.setVisible(true);
	}
}
