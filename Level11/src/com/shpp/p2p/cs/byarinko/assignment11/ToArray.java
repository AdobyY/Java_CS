package com.shpp.p2p.cs.byarinko.assignment11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The class is designed to convert the parameters passed to the Assignment11Part1 into an array
 */
public class ToArray {
    /**
     * Splits the expression into tokens and replaces the variable with their value if available
     * @param expressionAndParameters the expression and the variables whose values
     *                                are to be inserted into the expression
     * @return ArrayList of operands, operators and brackets
     */
    public static ArrayList<String> makeExpressionArray(String[] expressionAndParameters) {
        if (expressionAndParameters.length == 0 || expressionAndParameters[0].isEmpty()) {
            System.out.println("You did not pass an expression");
            System.exit(1);
        }

        String expression = expressionAndParameters[0];
        ArrayList<String> parameters = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(expressionAndParameters, 1, expressionAndParameters.length)));
        ArrayList<String> expressionArray = tokenize(expression.replaceAll("\\s", ""));

        return substituteVariables(expressionArray, parameters);
    }

    /**
     * Replaces the element of the array with its value in the HashMap
     */
    public static ArrayList<String> substituteVariables(ArrayList<String> expression, ArrayList<String> variables) {
        // HashMap to store variable-value pairs
        HashMap<String, String> variableMap = getStringStringHashMap(variables);

        // Substitute variables in the expression
        ArrayList<String> result = new ArrayList<>();
        for (String element : expression) {
            // If the element is a variable, substitute its value
            if (variableMap.containsKey(element)) {
                result.add(variableMap.get(element));

            } else if (element.startsWith("-") && // Handle the case where the variable and element start with minus
                    variableMap.containsKey(element.substring(1))) {
                if (variableMap.get(element.substring(1)).startsWith("-")) {
                    result.add(variableMap.get(element.substring(1)).substring(1));

                } else {
                    result.add("-" + variableMap.get(element.substring(1)));
                }

            } else {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Fills the hashmap with values
     * @return HashMap
     */
    private static HashMap<String, String> getStringStringHashMap(ArrayList<String> variables) {
        HashMap<String, String> variableMap = new HashMap<>();

        for (String assignment : variables) {
            String[] parts = assignment.split("=");
            if (parts.length == 2) {
                String variable = parts[0].trim();
                String value = parts[1].replaceAll("\\s", "");
                variableMap.put(variable, value);
            }
        }
        return variableMap;
    }

    /**
     * Converts a given string to ArrayList
     * Example: "-sqrt(4)-1.0" to {"-sqrt", "(", "4", ")", "-", "1.0"}
     * @param input the string to split
     * @return ArrayList of operators, operands and brackets
     */
    public static ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        // Handle situations where the input starts with "-" or "("
        if (input.startsWith("-")) {
            currentToken.append("-");
        } else if (input.startsWith("(")) {
            tokens.add(String.valueOf(input.charAt(0)));
        } else {
            currentToken.append(input.charAt(0));
        }

        // Split the input
        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isLetterOrDigit(currentChar) || currentChar == '.') {
                currentToken.append(currentChar);

            } else if (currentChar == '(' || currentChar == ')' || isOperator(currentChar)) {
                addCurrentToken(tokens, currentToken);
                tokens.add(String.valueOf(currentChar));

            } else if (currentChar == '-') {
                // Check if the minus sign should be written separately or not
                if (input.charAt(i - 1) == ')' || Character.isLetterOrDigit(input.charAt(i - 1))) {
                    addCurrentToken(tokens, currentToken);
                    tokens.add(String.valueOf(currentChar));

                } else {
                    currentToken.append(currentChar);
                }
            }
        }

        addCurrentToken(tokens, currentToken);

        return tokens;
    }

    /**
     * Adds the token to the ArrayList and makes the token empty
     */
    private static void addCurrentToken(ArrayList<String> tokens, StringBuilder currentToken) {
        if (!currentToken.isEmpty()) {
            tokens.add(currentToken.toString());
            currentToken.setLength(0);
        }
    }

    /**
     * Returns true if the given token is operator except minus
     *
     * @param token operator to be checked
     * @return true if the token is "*", "+", "/", "^" false otherwise
     */
    private static boolean isOperator(char token) {
        return token == '+' || token == '*' || token == '/' || token == '^';
    }
}
