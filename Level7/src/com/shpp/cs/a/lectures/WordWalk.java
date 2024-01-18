package com.shpp.cs.a.lectures;


import acm.util.ErrorException;
import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class WordWalk extends TextProgram {
    /* The name of the dictionary file. */
    private static final String WORDS_FILE = "dictionary.txt";

    /* How many letters overlap between words. */
    private static final int NUM_OVERLAPPING_LETTERS = 2;

    public void run() {
        /* Build up a map from two-letter sequences to the words that start with those
         * sequences.
         */
        HashMap<String, ArrayList<String>> dictionary = null;
        try {
            dictionary = loadDictionary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Continuously ask the user for a word, then do a word walk! */
        while (true) {
            String word = readLine("Enter starting word: ").toUpperCase();
            doWordWalk(word, dictionary);
        }
    }

    /**
     * Given a word and a dictionary, does a word walk from the starting word
     * through the dictionary.
     *
     * @param word       The word to start from.
     * @param dictionary The dictionary of words.
     */
    private void doWordWalk(String word, HashMap<String, ArrayList<String>> dictionary) {
        while (true) {
            /* Print the current word. */
            println(word);

            /* Look up what the successor words are. */
            String suffix = word.substring(word.length() - NUM_OVERLAPPING_LETTERS);
            ArrayList<String> options = dictionary.get(suffix);

            /* If no words start with this sequence, we're done. */
            if (options == null)
                break;

            /* Otherwise, choose a random successor word. */
            RandomGenerator rgen = RandomGenerator.getInstance();
            word = options.get(rgen.nextInt(0, options.size() - 1));
        }
    }

    /**
     * Reads in the dictionary as a map from prefixes to words with that prefix.
     *
     * @return The dictionary, sorted by prefixes.
     */
    private HashMap<String, ArrayList<String>> loadDictionary() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(WORDS_FILE));
            HashMap<String, ArrayList<String>> result =
                    new HashMap<String, ArrayList<String>>();

            while (true) {
                String line = br.readLine();
                if (line == null) break;

                /* Determine the prefix of the current word. */
                String prefix = line.substring(0, NUM_OVERLAPPING_LETTERS);

                /* Ensure that there is something in the HashMap for this prefix. */
                if (!result.containsKey(prefix))
                    result.put(prefix, new ArrayList<String>());

                /* Add the word to the list of strings starting with its prefix. */
                result.get(prefix).add(line);
            }

            br.close();
            return result;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}