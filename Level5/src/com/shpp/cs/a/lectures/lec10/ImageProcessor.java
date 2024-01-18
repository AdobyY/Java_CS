package com.shpp.cs.a.lectures.lec10;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

public class ImageProcessor extends WindowProgram {
	/* Size of the window. */
	public static final int APPLICATION_HEIGHT = 700;
	public static final int APPLICATION_WIDTH  = 750;

	/**
	 * Constructs a red/green spectrum image showing all possible colors
	 * that can be made from red and green.
	 *
	 * @return A red/green color spectrum.
	 */
	private GImage makeSpectrum() {
		int[][] pixels = new int[256][256];

		/* Each element gets its red component from its row and its green component
		 * from its column.
		 */

		for (int row = 0; row < pixels.length; row++)
			for (int col = 0; col < pixels[row].length; col++)
				//                                        R   G   B
				pixels[row][col] = GImage.createRGBPixel(row, col, 0);

		return new GImage(pixels);
	}

	/**
	 * Given an image, returns a new version of the image that has the
	 * red channel disabled.
	 *
	 * @param source The original image.
	 * @return A new image equal to the original, but with no red components.
	 */
	private GImage disableRed(GImage source) {
		int[][] pixels = source.getPixelArray();

		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[row].length; col++) {
				/* Extract the green and blue components and write them back, leaving the
				 * red component out.
				 */
				int green = GImage.getGreen(pixels[row][col]);
				int blue = GImage.getBlue(pixels[row][col]);
				int red = GImage.getRed(pixels[row][col]);

				//                                       R    G    B
				pixels[row][col] = GImage.createRGBPixel(0, green, blue);
			}
		}

		return new GImage(pixels);
	}

	/**
	 * Given an image, "psychedelifies" it by rotating the color components
	 * forward one step.
	 *
	 * @param source The source image.
	 * @return A "psychedelified" version of the image.
	 */
	private GImage psychedelify(GImage source) {
		int[][] pixels = source.getPixelArray();

		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[row].length; col++) {
				/* Extract the color components/ */
				int red = GImage.getRed(pixels[row][col]);
				int green = GImage.getGreen(pixels[row][col]);
				int blue = GImage.getBlue(pixels[row][col]);

				/* Rotate the colors forward one step. */
				//                                        R      G     B
				pixels[row][col] = GImage.createRGBPixel(green, blue, red);
			}
		}

		return new GImage(pixels);
	}

	public void run() {
		GImage rainbowDash = new GImage("assets/rainbow-dash.png");

		/* A wonderfully color-warped animation. */		//noinspection InfiniteLoopStatement
		while (true) {
			removeAll();
			add(rainbowDash);
			pause(1000);

			rainbowDash = psychedelify(rainbowDash);
		}
	}
}
