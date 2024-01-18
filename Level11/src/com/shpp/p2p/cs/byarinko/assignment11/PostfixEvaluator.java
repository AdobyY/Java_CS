    package com.shpp.p2p.cs.byarinko.assignment11;

    import java.util.ArrayList;
    import java.util.Stack;
    import java.lang.Math;

    /**
     * This class is designed to evaluate a postfix expression
     */
    public class PostfixEvaluator {

        /**
         * Evaluates a postfix expression
         * @param postfixExpression expression to be evaluated
         * @return the result of the evaluated expression
         */
        public static double evaluatePostfix(ArrayList<String> postfixExpression) {
            Stack<Double> stack = new Stack<>();

            for (String token : postfixExpression) {
                if (isOperand(token)) {
                    stack.push(Double.parseDouble(token));
                } else if (isOperator(token)) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    double result = performOperation(operand1, operand2, token);
                    stack.push(result);
                } else if (isFunction(token)) {
                    double operand = stack.pop();
                    double result = performFunction(token, operand);
                    stack.push(result);
                } else {
                    System.out.println("Maybe you should close the parenthesis?");
                    System.exit(1);
                }
            }

            return stack.pop();
        }

        private static boolean isOperand(String str) {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        /**
         * Checks if the token is an operator
         * @param token token to be checked
         * @return true if token is an operator false otherwise
         */
        private static boolean isOperator(String token) {
            return token.equals("+") || token.equals("-") || token.equals("*")
                                     || token.equals("/") || token.equals("^");
        }

        /**
         * Checks if the token is a function
         * @param token token to be checked
         * @return true if token is a function false otherwise
         */
        private static boolean isFunction(String token) {
            return token.equals("cos") || token.equals("sin") || token.equals("tan")
                    || token.equals("atan") || token.equals("log10") || token.equals("log2")
                    || token.equals("sqrt") || token.equals("-cos") || token.equals("-sin") || token.equals("-tan")
                    || token.equals("-atan") || token.equals("-log10") || token.equals("-log2")
                    || token.equals("-sqrt");
        }

        /**
         * Performs an operation with two operands
         * @param operand1 left operand
         * @param operand2 right operand
         * @param operator action (+-/*^)
         * @return result of the operation
         */
        private static double performOperation(double operand1, double operand2, String operator) {
            return switch (operator) {
                case "+" -> operand1 + operand2;
                case "-" -> operand1 - operand2;
                case "*" -> operand1 * operand2;
                case "/" -> operand1 / operand2;
                case "^" -> Math.pow(operand1, operand2);
                default -> throw new IllegalArgumentException("Invalid operator: " + operator);
            };
        }

        /**
         * Calculates a function for an operand
         * @param function the function to calculate the operand
         * @param operand the number to be operated on
         * @return result of the function
         */
        private static double performFunction(String function, double operand) {
            return switch (function) {
                case "cos" -> Math.cos(operand);
                case "sin" -> Math.sin(operand);
                case "tan" -> Math.tan(operand);
                case "atan" -> Math.atan(operand);
                case "log10" -> Math.log10(operand);
                case "log2" -> Math.log(operand) / Math.log(2);
                case "sqrt" -> Math.sqrt(operand);
                case "-cos" -> -Math.cos(operand);
                case "-sin" -> -Math.sin(operand);
                case "-tan" -> -Math.tan(operand);
                case "-atan" -> -Math.atan(operand);
                case "-log10" -> -Math.log10(operand);
                case "-log2" -> -Math.log(operand) / Math.log(2);
                case "-sqrt" -> -Math.sqrt(operand);
                default -> throw new IllegalArgumentException("Invalid function: " + function);
            };
        }
    }
