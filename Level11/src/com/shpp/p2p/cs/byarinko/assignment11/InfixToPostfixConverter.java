package com.shpp.p2p.cs.byarinko.assignment11;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class is designed to convert an infix expression to a postfix expression
 */
public class InfixToPostfixConverter {
    /**
     * Convert infix expression to postfix expression
     * @param infixExpression expression to be converted
     * @return postfix expression
     */
    public static ArrayList<String> infixToPostfix(ArrayList<String> infixExpression) {
        ArrayList<String> postfixList = new ArrayList<>();
        // Used to temporarily hold operators and parentheses during the conversion process
        Stack<String> stack = new Stack<>();

        for (String token : infixExpression) {
            if (isOperand(token)) {
                postfixList.add(token);
            } else if (isOperator(token)) {
                /* Compares operator precedence with the operator at the top of the stack.
                If the stack's operator has higher or equal precedence, it is popped from the stack
                and added to the postfixList. This process is repeated
                until the stack is empty or the top operator has lower precedence.
                 */
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                    postfixList.add(stack.pop());
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                /*
                Pops operators from the stack and adds them to postfixList until an open parenthesis
                is encountered on the stack.
                 */
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfixList.add(stack.pop());
                }
                if(!stack.isEmpty()) {
                    stack.pop(); // Pop the left parenthesis
                }else{
                    System.out.println("Maybe you forgot to open the bracket?");
                }
            }
        }

        while (!stack.isEmpty()) {
            postfixList.add(stack.pop());
        }

        return postfixList;
    }

    /**
     * Determines whether the token is an operand (number)
     * @param token number to be checked
     * @return true if the token is an operand false otherwise
     */
    private static boolean isOperand(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if the given token is operator or function such as sin, tan, or log2
     * @param token operator to be checked
     * @return true if the token is a unary or binary operator false otherwise
     */
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")
                || token.equals("^") || token.equals("sin") || token.equals("cos") || token.equals("tan")
                || token.equals("atan") || token.equals("log10") || token.equals("log2") || token.equals("sqrt")
                || token.equals("-sin") || token.equals("-cos") || token.equals("-tan")|| token.equals("-atan")
                || token.equals("-log10") || token.equals("-log2") || token.equals("-sqrt");
    }

    /**
     * Determines the priority of the operation
     * @param operator the operator or function
     * @return priority
     */
    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            case "sin", "cos", "tan", "atan", "log10", "log2", "sqrt",
                "-sin", "-cos", "-tan", "-atan", "-log10", "-log2", "-sqrt"-> 4;
            default -> -1;
        };
    }

}
