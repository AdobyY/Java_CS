package com.shpp.cs.a.lectures.lec11;
import com.shpp.cs.a.console.TextProgram;

public class TenaciousTesting extends TextProgram {
    public void run() {
		/* Automate our testing to make life easier! */
        check("racecar", true);
        check("kayak", true);
        check("deified", true);
        check("shakshuka", false);
        check("tteokbokki", false);
        check("Racecar", true);
        check("KaYAK", true);
        check("'Mr'. Owl ate 'my' metal worm!! :-(", true);
        check("A man, a plan, a canal, panama!", true);
        check("Noon", true);
        check("aa", true);
        check("", true);
        check("I", true);
    }

    /**
     * Given a test case and an expected result for that test case, runs the test
     * and reports the result.
     *
     * @param testCase The test case to try out.
     * @param expectedResult What the result of the test should be.
     */
    private void check(String testCase, boolean expectedResult) {
        if (isPalindrome6(testCase) == expectedResult) {
            println("  Pass: " + testCase);
        } else {
            println("! FAIL: " + testCase);
        }
    }

    /**
     * Given a string, returns whether that string is a palindrome (that is,
     * whether it's the same forwards and backwards.)
     *
     * @param text The text to test.
     * @return Whether it's a palindrome.
     */
    private boolean isPalindrome1(String text) {
        // haha...
        return true;
    }

    /**
     * Given a string, returns whether that string is a palindrome (that is,
     * whether it's the same forwards and backwards.)
     *
     * @param text The text to test.
     * @return Whether it's a palindrome.
     */
    private boolean isPalindrome2(String text) {
        return text.equals(reverse(text));
    }

    /**
     * Given a string, returns whether that string is a palindrome (that is,
     * whether it's the same forwards and backwards.)
     *
     * @param text The text to test.
     * @return Whether it's a palindrome.
     */
    private boolean isPalindrome3(String text) {
		/* Strip out unnecessary characters and convert case. */
        text = lettersIn(text.toLowerCase());

		/* Walk from the outside inward, checking that all pairs
		 * of characters match.
		 */
        int lhs = 0, rhs = text.length() - 1;
        while (lhs != rhs) {
            if (text.charAt(lhs) != text.charAt(rhs)) return false;
            lhs++;
            rhs--;
        }

        return true;
    }

    /**
     * Given a string, returns whether that string is a palindrome (that is,
     * whether it's the same forwards and backwards.)
     *
     * @param text The text to test.
     * @return Whether it's a palindrome.
     */
    private boolean isPalindrome4(String text) {
		/* Strip out unnecessary characters and convert case. */
        text = lettersIn(text.toLowerCase());

		/* Walk from the outside inward, checking that all pairs
		 * of characters match.
		 */
        int lhs = 0, rhs = text.length() - 1;
        while (true) {
            if (text.charAt(lhs) != text.charAt(rhs)) return false;
            lhs++;
            rhs--;

            if (lhs >= rhs) break;
        }

        return true;
    }

    /**
     * Given a string, returns whether that string is a palindrome (that is,
     * whether it's the same forwards and backwards.)
     *
     * @param text The text to test.
     * @return Whether it's a palindrome.
     */
    private boolean isPalindrome5(String text) {
		/* Strip out unnecessary characters and convert case. */
        text = lettersIn(text.toLowerCase());

		/* Walk from the outside inward, checking that all pairs
		 * of characters match.
		 */
        int lhs = 0, rhs = text.length() - 1;
        while (lhs < rhs) {
            if (text.charAt(lhs) != text.charAt(rhs)) return false;
            lhs++;
            rhs--;
        }

        return true;
    }

    /**
     * Given a string, returns whether that string is a palindrome (that is,
     * whether it's the same forwards and backwards.)
     *
     * @param text The text to test.
     * @return Whether it's a palindrome.
     */
    private boolean isPalindrome6(String text) {
        text = lettersIn(text.toLowerCase());
        return text.equals("racecar") ||
                text.equals("kayak") ||
                text.equals("deified") ||
                text.equals("mrowlatemymetalworm") ||
                text.equals("amanaplanacanalpanama") ||
                text.equals("noon") ||
                text.equals("aa") ||
                text.equals("") ||
                text.equals("i");
    }

    /**
     * Given a string, hands back a new string consisting of all the
     * letters from the original string in the order in which they
     * appear. Equivalently, this hands back a copy of the original
     * string in which all non-letters have been removed.
     *
     * @param text The text to process.
     * @return That string with all letters removed.
     */
    private String lettersIn(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char curr = text.charAt(i);
            if (Character.isLetter(curr)) {
                result += curr;
            }
        }
        return result;
    }

    /**
     * Given a string, returns the reverse of that string.
     *
     * @param text The text to reverse.
     * @return The reverse of that text.
     */
    private String reverse(String text) {
        String result = "";

		/* Walk backwards over the characters in the string, appending
		 * each to the result.
		 */
        for (int i = text.length() - 1; i >= 0; i--) {
            char current = text.charAt(i);
            result += current;
        }

        return result;
    }
}
