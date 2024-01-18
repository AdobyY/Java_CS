package com.shpp.p2p.cs.byarinko.a.lectures.lec15;

import java.awt.*;
import javax.swing.*;

/* A component that draws a blue circle that fills the window. */
public class BlueCircleComponent extends JComponent {

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 400);
	}

	public void paintComponent(Graphics g) {
		/* Clear the window. */
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		/* Draw a blue oval. */
		g.setColor(Color.BLUE);
		g.fillOval(0, 0, getWidth(), getHeight());
	}
}