package com.myapp.calculator;

/**
 * Android calculator app
 */
public class DigitUnit implements ExpressionUnit {

    final String digit;

    public DigitUnit(String digit) {
        this.digit = digit;
    }

    @Override
    public String getText() {
        return digit;
    }


}
