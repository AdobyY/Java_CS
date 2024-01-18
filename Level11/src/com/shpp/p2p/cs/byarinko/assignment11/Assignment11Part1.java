package com.shpp.p2p.cs.byarinko.assignment11;

import java.util.ArrayList;

/**
 * An advanced calculator, this time it even supports functions and parentheses. Try it!
 */
public class Assignment11Part1 {
    public static void main(String[] strings) {
        if(strings.length > 0){
            System.out.println("Your expression:");
        }
        for (String string : strings) {
            System.out.println("\t" + string);
        }
        ArrayList<String> expressionArray = ToArray.makeExpressionArray(strings);

        ArrayList<String> postfixExpression = InfixToPostfixConverter.infixToPostfix(expressionArray);

        double result = PostfixEvaluator.evaluatePostfix(postfixExpression);
        System.out.println(Double.isNaN(result) ? "The result is too large to calculate" : "Result: " + result);
    }
}
