package com.shpp.p2p.cs.byarinko.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {
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
        word = word.toLowerCase();

        // Vowel counter
        int syllableCount = 0;

        // Initialize a flag that indicates whether we are in front of a vowel.
        boolean beforeVowel = false;

        // For each letter in the word
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            // If the current letter is a vowel, then we increase the syllable counter,
            // if we are not in front of another vowel before it.
            if (isVowel(currentChar) && !beforeVowel) {
                syllableCount++;
                beforeVowel = true;
            }
            // If the current letter is not a vowel, then set the flag before the vowel to false.
            else if (!isVowel(currentChar)) {
                beforeVowel = false;
            }
        }

        // Check whether the word ends with "e"
        if (word.length() > 1 && word.endsWith("e") && !isVowel(word.charAt(word.length() - 2))) {
            syllableCount--;
        }

        // At least 1 syllable in the word
        if (syllableCount <= 0) {
            return 1;
        }

        return syllableCount;
    }

    /**
     * Checks whether letter is vowel
     * @param c letter that should be checked
     * @return true if vowel and false otherwise
     */
    private boolean isVowel(char c) {
        return "aeiouy".contains(String.valueOf(c));
    }

}
