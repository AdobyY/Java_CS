package com.shpp.cs.a.lectures.lec12;


import acm.graphics.GPoint;
import com.shpp.cs.a.console.TextProgram;

import java.util.*;
import java.io.*;

public class ExploreUA extends TextProgram {
    /* The name of the US cities file. */
    private static final String CITIES_FILE = "assets/shpp-cs-a-uacities-ptypes.txt";

    public void run() {

        HashMap<String, GPoint> cities = readUACities();
        while (true) {
            String cityName = readLine("Enter a city: ").toLowerCase();
            if (cities.containsKey(cityName)) {
                println("  That city is at " + cities.get(cityName));
            } else {
                println("  I am so, so, terribly, terribly, sorry. :-(");
            }
        }
    }

    /**
     * Reads the cities file and returns a map from city names to census
     * locations. The city names are all stored in lower case.
     *
     * @return A map from city names to city locations.
     */
    private HashMap<String, GPoint> readUACities() {
        HashMap<String, GPoint> result = new HashMap<String, GPoint>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(CITIES_FILE));

            while (true) {
				/* Read the name, latitude, and longitude of the file. */
                String name = br.readLine();
                String type = br.readLine();
                String latitude = br.readLine();
                String longitude = br.readLine();

				/* If we've run out of cities, we're done. */
                if (longitude == null) {
                    break;
                }

                double xlongitude = Double.parseDouble(longitude);
				/* Construct a GPoint from the data and add it to the result. */
                GPoint pt = new GPoint(xlongitude, Double.parseDouble(latitude));

				/* Store the name in lower-case. */
                result.put(name.toLowerCase(), pt);
            }

            br.close();
        } catch (IOException e) {
            // Do nothing...
        }

        return result;
    }
}
