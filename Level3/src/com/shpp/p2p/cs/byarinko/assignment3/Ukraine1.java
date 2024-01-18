package com.shpp.p2p.cs.byarinko.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ukraine1 extends WindowProgram {
    /* The name of the cities file. */
    private static final String CITIES_FILE = "assets/shpp-cs-a-uacities-ptypes.txt";

    public void run() {
        try {
			/* Open the file for reading. */
            BufferedReader br = new BufferedReader(new FileReader(CITIES_FILE));

			/* Read the file in triples, plotting a dot for each city. */
            while (true) {
                String cityName = br.readLine();
                String cityType = br.readLine();
                String cityLat  = br.readLine();
                String cityLong = br.readLine();

				/* If *any* of the above three variables is null, then the longitude
				 * will be null because its call to readLine won't have any data left.
				 */
                if (cityLong == null) break;

                plotOneCity(cityName, cityLat, cityLong);
            }

            br.close();
        } catch (IOException e) {
            println("How unpatriotic.");
        }
    }

    /**
     * Given the longitude and latitude of a city (in string form), displays
     * a dot for that city.
     *
     * @param cityLat The latitude, in degrees north.
     * @param cityLong The longitude, in degrees east.
     */
    private void plotOneCity(String cityName, String cityLat, String cityLong) {
		/* Convert from a text form into real numbers. */
        double longitude = Double.parseDouble(cityLong);
        double latitude  = Double.parseDouble(cityLat);

		/* Determine where on screen the city should be drawn. */
        double x = longitudeToXCoordinate(longitude);
        double y = latitudeToYCoordinate(latitude);

        if (cityName.equals("Kirovohrad"))
            plotPixel(x, y, Color.RED, 3);
        else
            plotPixel(x, y, Color.LIGHT_GRAY,1);
    }

    /**
     * Plots a pixel at the specified (x, y) coordinate.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    private void plotPixel(double x, double y, Color color, int size)  {
		/* Create a 1x1 pixel of the given color. */
        GRect pixel = new GRect(x, y, size, size);
        pixel.setFilled(true);
        pixel.setColor(color);
        add(pixel);
    }

    /**
     * Given a raw longitude, returns the screen x coordinate where
     * it should be displayed.
     *
     * @param longitude The longitude in question.
     * @return Where it maps to as an x coordinate.
     */
    private double longitudeToXCoordinate(double longitude) {
        return getWidth() *
                (longitude - MIN_LONGITUDE) /
                    (MAX_LONGITUDE - MIN_LONGITUDE);
    }

    /**
     * Given a raw latitude, returns the screen y coordinate where
     * it should be displayed.
     *
     * @param latitude The latitude in question.
     * @return Where it maps to as a y coordinate.
     */
    private double latitudeToYCoordinate(double latitude) {
        return getHeight() * (1.0 - (latitude - MIN_LATITUDE) / (MAX_LATITUDE - MIN_LATITUDE));
    }


	/* * * * * Constants to control the graphics and behavior. * * * * */

    /* Make the window large so that we can see more detail. */
    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 750;

    /* The viewpoint coordinates - the minimum and maximum longitude
     * and latitude.
     */
    // longitude == x
    private static final double MIN_LONGITUDE = 22;
    private static final double MAX_LONGITUDE = 42;

    // latitude == y
    private static final double MAX_LATITUDE = +53;
    private static final double MIN_LATITUDE = +42;

}