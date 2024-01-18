package com.shpp.p2p.cs.byarinko.assignment10;

import static java.lang.Double.parseDouble;

/**
 * Simple calculator.
 * Pass the expression and variable values as arguments and get the result
 */
public class Assignment10Part1 {

    /**
     * The expression to be calculated.
     */
    static String expression;

    /**
     * Index of Left number is needed to find the number
     * that is on the left side relative to the operator
     */
    static int indexOfLeftNumber;

    /**
     * Index of Right number is needed to find the number
     * that is on the right side relative to the operator
     */
    static int indexOfRightNumber;

    /**
     * Action sequence counter
     */
    static int action = 1;

    /**
     * The left operand with which the operation is performed
     */
    static StringBuilder leftNumber;

    /**
     * The right operand with which the operation is performed
     */
    static StringBuilder rightNumber;

    /**
     * Accepts one mandatory parameter - an expression, and optional - variables and their values
     */
    public static void main(String[] args) {
        if (args.length > 0) expression = args[0];
        else {System.out.println("Ви не передали нічого"); System.exit(1);}

        // Print expression
        System.out.println("Your expression: " + expression);

        // Print variables
        for (int i = 1; i < args.length; i++) {
            System.out.println(args[i]);
        }

        // Insert the values of the variables into the expression
        for (int i = 1; i < args.length; i++) {
            String[] arguments = args[i].split("=");

            if (arguments.length == 2) {
                String variable = arguments[0].trim();
                String value = arguments[1].trim();
                expression = expression.replace(variable, value).replaceAll("\\s", "");
            }
        }

        // Simplify the expression
        expression = simplifyExpression(expression);
        System.out.println("Final expression: " + expression);

        // Calculate the expression
        try {
            calculate(expression);
            System.out.println("    Your solution: " + expression);
            System.out.println();
        } catch (Exception e) {
            System.out.println("    Wrong expression!!!");
            System.out.println();
        }
    }

    /**
     * Replaces all "+-" with "-" and "--" with "" to remove redundant operators
     *
     * @param expression that needs to be simplified
     * @return simplified expression
     */
    private static String simplifyExpression(String expression) {
        expression = expression.replaceAll("\\+-", "-").replaceAll("--", "");
        return expression;
    }

    /**
     * Performs each operation that is in the expression
     * according to the priority of the operators
     *
     * @param exp expression that needs to be calculated
     */
    private static void calculate(String exp) {
        // Update the action counter
        action = 1;

        // Split expression into symbols
        String[] expressionArrayOfSymbols = exp.split("");

        // Array of operators which must be calculated sequentially
        String[] operators = {"^", "*", "/", "+", "-"};

        for (String o : operators) {
            for (String symbol : expressionArrayOfSymbols) {

                if (!containsOperator()) break;

                if (o.contains(symbol)) {
                    double calc = makeAction(symbol);

                    // Make new expression with the result of the previous action
                    expression = expression.substring(0, indexOfLeftNumber) + calc + expression.substring(indexOfRightNumber);

//                  Comment this if you don't want to show steps
                    showSteps(leftNumber, o, rightNumber, calc, expression);
                }
            }
        }
    }

    /**
     * Checks if there is still some action to be taken in the expression
     * @return true if expression contains any operator false otherwise
     */
    private static boolean containsOperator() {
        String operatorsToCheck = "/*-+^";
        boolean containsOperator = false;

        for (int j = 0; j < operatorsToCheck.length(); j++) {
            char symbolToCheck = operatorsToCheck.charAt(j);
            if (expression.substring(1).contains(String.valueOf(symbolToCheck))) {
                containsOperator = true;
                break;
            }
        }
        return containsOperator;
    }

    /**
     * Prints the sequence of expression evaluation
     *
     * @param leftNumber left operand
     * @param operator operator
     * @param rightNumber right operand
     * @param result their result
     * @param expression the expression in which the action is performed
     */
    private static void showSteps(StringBuilder leftNumber, String operator,
                                  StringBuilder rightNumber, double result, String expression) {
        System.out.println("    " + action + ") " + leftNumber + operator + rightNumber + " = " + result);
        action++;
        System.out.println(expression);
    }

    /**
     * Performs the action for which the operator is responsible
     * @param operator the action to be performed
     * @return action result
     */
    private static double makeAction(String operator) {
        return switch (operator) {
            case "/" -> findLeftNumber(operator) / findRightNumber(operator);
            case "*" -> findLeftNumber(operator) * findRightNumber(operator);
            case "^" -> Math.pow(findLeftNumber(operator), findRightNumber(operator));
            case "+" -> findLeftNumber(operator) + findRightNumber(operator);
            case "-" -> findLeftNumber(operator) - findRightNumber(operator);
            default -> 0;
        };
    }

    /**
     * Finds the left operand to be operated on
     * @param operator the action to be performed
     * @return left operand
     */
    private static double findLeftNumber(String operator) {
        StringBuilder leftNum = new StringBuilder();
        int i = 0;

        // If expression starts with "-" then find index of the second "-"
        // otherwise find first index of right number
        if (expression.startsWith("-")) {
            i += expression.indexOf(operator, expression.indexOf("-") + 1) - 1;
        } else {
            i += expression.indexOf(operator) - 1;
        }

        // While current symbol is not operator then add symbol to left number
        while (i >= 0) {
            if ("/*+-^".indexOf(expression.charAt(i)) == -1 || i == 0) {
                leftNum.append(expression.charAt(i));
            } else {
                break;
            }

            indexOfLeftNumber = i;
            i--;
        }
        leftNumber = leftNum;

        return parseDouble(String.valueOf(leftNum.reverse()));
    }

    /**
     * Finds the right operand to be operated on
     * @param operator the action to be performed
     * @return right operand
     */
    private static double findRightNumber(String operator) {
        StringBuilder rightNum = new StringBuilder();
        int i = 0;

        // If expression starts with "-" then find index of the second "-"
        // otherwise find first index of right number
        if (expression.startsWith("-")) {
            i += expression.indexOf(operator, expression.indexOf("-") + 1) + 1;
        } else {
            i += expression.indexOf(operator) + 1;
        }
        rightNum.append(expression.charAt(i));
        i++;

        // While current symbol is not operator then add symbol to right number
        while (i < expression.length()) {
            if ("/*+-^".indexOf(expression.charAt(i)) == -1) {
                rightNum.append(expression.charAt(i));
            } else {
                break;
            }
            i++;
        }
        indexOfRightNumber = i;

        rightNumber = rightNum;

        return parseDouble(String.valueOf(rightNum));
    }
}
