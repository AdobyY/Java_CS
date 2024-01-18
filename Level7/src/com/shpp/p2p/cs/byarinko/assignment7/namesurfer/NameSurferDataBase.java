package com.shpp.p2p.cs.byarinko.assignment7.namesurfer;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {
    private final HashMap<String, NameSurferEntry> database;
    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename)  {
        database = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while(true) {
                String line = br.readLine();
                if (line == null) break;
                NameSurferEntry entry = new NameSurferEntry(line.toLowerCase());
                database.put(entry.getName(), entry);
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Error!");
        }
    }

	/* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return database.get(name);
    }
}

