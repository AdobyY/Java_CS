package com.shpp.p2p.cs.byarinko.assignment7.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {

    private final String name;
    private final int[] ranks;
	/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        // Split the string into words and numbers
        String[] parts = line.split(" ");
        name = parts[0];
        ranks = new int[NDECADES];
        for (int i = 0; i < NDECADES; i++) {
            ranks[i] = Integer.parseInt(parts[i + 1]);
        }
    }

	/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {return name;}

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
            return ranks[decade];
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        StringBuilder result = new StringBuilder(name + " [");
        for (int i = 0; i < NDECADES - 1; i++) {
            result.append(ranks[i]).append(" ");
        }
        result.append(ranks[NDECADES - 1]).append("]");
        return result.toString();
    }
}

