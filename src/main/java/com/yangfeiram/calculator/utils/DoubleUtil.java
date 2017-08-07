package com.yangfeiram.calculator.utils;

/**
 * double type check util
 */
public class DoubleUtil {

    public static Double tryParseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
