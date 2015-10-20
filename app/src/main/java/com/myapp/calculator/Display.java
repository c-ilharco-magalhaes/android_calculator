package com.myapp.calculator;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Android calculator app
 */

// Helper for displaying input and output correctly.
public class Display {

    static final boolean expressionDisplayHelper = true;

    static final Set<String> basicOperators = new HashSet<>(Arrays.asList("+", "-", "/", "*"));
    static final Set<String> allowedOpOverlap = new HashSet<>(Arrays.asList("*-", "/-"));
    static final Map<String, String> exceptionOpOverlap = new HashMap<String, String>() {{
        put("--", "+");
        put("*+", "*");
        put("/+", "/");
    }};

    // TODO: Handle edge cases such as '.' + '.' = '.' or '-' + '-' = '+'.
    public static String getExpressionDisplay (String expression, String buttonPressed){

        if (buttonPressed.equals("del")){
            return expression.isEmpty() ? "" : expression.substring(0, expression.length()-1);
        } else if (buttonPressed.equals("clear")){
            return "";
        }

        if (expressionDisplayHelper){
            if (buttonPressed.equals(".")){
                return addDecimalPoint(expression);
            } else if (basicOperators.contains(buttonPressed)){
                return addBasicOperator(expression, buttonPressed);
            }
        }
        return expression + buttonPressed;
    }

    public static String getResultDisplay (String expression){
        return Kernel.evaluate(expression);
    }

    // Check if decimal point insertion is valid.
    private static String addDecimalPoint (String expression){
        String regex = ".*\\.\\d*\\z";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()){
            return expression;        // Invalid insertion.
        } else{
            return expression + ".";  // Valid insertion.
        }
    }

    // If the last character from expression is a basic operator, replace it except for a few cases.
    // ++ = +,  +- = -,  +* = *, +/ = /, -+ = +, -/ = /, -* = *, ** = *, */ = /, // = /, /+ = /, /* = *
    // -- = + , *- = *-,  *+ = *, /- = /-
    private static String addBasicOperator (String expression, String op){
        if (expression.isEmpty()){
            return op.equals("-") ? op : "";
        }
        int l = expression.length();
        String lastChar = "" + expression.charAt(l-1);
        if (basicOperators.contains(lastChar)){
            if (allowedOpOverlap.contains(lastChar + op)){
                return expression + op;
            } else if (exceptionOpOverlap.containsKey(lastChar + op)){
                return addBasicOperator(expression.substring(0,l-1), exceptionOpOverlap.get(lastChar + op));
            } else {
                return addBasicOperator(expression.substring(0,l-1), op);
            }
        } else {
            return expression + op;
        }
    }

}
