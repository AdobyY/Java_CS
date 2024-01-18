package com.shpp.p2p.cs.byarinko.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class SyllableCounting extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        int numOfSyllables = 0;
        boolean previousIsVowel = false;
        String vowels = "aeiouyAEIOUY";

        for (char letter : word.toCharArray()) {
            char lowercaseLetter = Character.toLowerCase(letter);
            if (vowels.indexOf(lowercaseLetter) != -1) {
                if (!previousIsVowel) {
                    numOfSyllables++;
                    previousIsVowel = true;
                }
            } else {
                previousIsVowel = false;
            }
        }

        // Adjust for special cases
        if (word.endsWith("e") || word.endsWith("E")) {
            numOfSyllables--;
        }

        // At least one syllable for words with no vowels
        return Math.max(numOfSyllables, 1);
    }
}

