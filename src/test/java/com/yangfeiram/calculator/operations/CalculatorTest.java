package com.yangfeiram.calculator.operations;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void initial() {
        calculator = new Calculator();
    }

    @After
    public void release() {
        calculator.clearStacks();
        calculator = null;
    }

    @Test
    public void calculatorTest() {
        System.out.println(calculator.getCurrentResultWithExpression(""));
        calculator.clearStacks();
        //<editor-fold desc="basic testing cases">
        assertTrue("testCase1 failed", "stack: 5 2"
                .equals(calculator.getCurrentResultWithExpression("5 2")));
        calculator.clearStacks();

        assertTrue("testCase2 failed", "stack: 3"
                .equals(calculator.getCurrentResultWithExpression("2 sqrt clear 9 sqrt")));
        calculator.clearStacks();

        assertTrue("testCase3 failed", "stack:"
                .equals(calculator.getCurrentResultWithExpression("5 2 - clear")));
        calculator.clearStacks();

        assertTrue("testCase4 failed", "stack: 20 5"
                .equals(calculator.getCurrentResultWithExpression("5 4 3 2 undo undo * 5 * undo")));
        calculator.clearStacks();

        assertTrue("testCase5 failed", "stack: 10.5"
                .equals(calculator.getCurrentResultWithExpression("7 12 2 / * 4 /")));
        calculator.clearStacks();

        assertTrue("testCase6 failed", "stack: -1"
                .equals(calculator.getCurrentResultWithExpression("1 2 3 4 5 * clear 3 4 -")));
        calculator.clearStacks();

        assertTrue("testCase7 failed", "stack: 120"
                .equals(calculator.getCurrentResultWithExpression("1 2 3 4 5 * * * *")));
        calculator.clearStacks();

        assertTrue("testCase8 failed", "operator * (position: 15): insufficient parameters\nstack: 11"
                .equals(calculator.getCurrentResultWithExpression("1 2 3 * 5 + * * 6 5")));
        calculator.clearStacks();
        //</editor-fold>

        //<editor-fold desc="boundary value testing cases">
        assertTrue("testCase9 failed", "input could not be null\nstack:"
                .equals(calculator.getCurrentResultWithExpression(null)));
        calculator.clearStacks();

        assertTrue("testCase10 failed", "input could not be null\nstack:"
                .equals(calculator.getCurrentResultWithExpression("")));
        calculator.clearStacks();

        assertTrue("testCase11 failed", "operator undo (position: 1): insufficient parameters\nstack:"
                .equals(calculator.getCurrentResultWithExpression("undo undo")));
        calculator.clearStacks();

        assertTrue("testCase12 failed", "operator sqrt (position: 1): insufficient parameters\nstack:"
                .equals(calculator.getCurrentResultWithExpression("sqrt")));
        calculator.clearStacks();

        assertTrue("testCase13 failed", "operator sqrt (position: 7): insufficient parameters\nstack:"
                .equals(calculator.getCurrentResultWithExpression("2 sqrt clear sqrt")));
        calculator.clearStacks();
        //</editor-fold>

        //<editor-fold desc="decimal places testing cases">
        assertTrue("testCase14 failed", "stack: 1.4142135623"
                .equals(calculator.getCurrentResultWithExpression("2 sqrt")));
        calculator.clearStacks();
        //</editor-fold>

    }
}
