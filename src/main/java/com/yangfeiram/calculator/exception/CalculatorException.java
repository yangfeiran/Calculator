package com.yangfeiram.calculator.exception;

/**
 * Self-defined Exception, throwed when there is an operation error
 */
public class CalculatorException extends Exception {
    public CalculatorException(String message) {
        super(message);
    }
}
