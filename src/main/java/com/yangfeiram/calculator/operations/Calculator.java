package com.yangfeiram.calculator.operations;

import com.yangfeiram.calculator.exception.CalculatorException;
import com.yangfeiram.calculator.model.Instruction;
import com.yangfeiram.calculator.utils.DoubleUtil;
import com.yangfeiram.calculator.utils.OperatorUtil;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Stack;

/**
 * Created by Administrator on 2017/8/4.
 */
public class Calculator {
    private static Stack<Double> current = new Stack<>();
    private static Stack<Instruction> history = new Stack<Instruction>();
    private static boolean undoFlag = false;

    /**
     * check the input and calculate the result
     *
     * @param input one line of the numbers and the operators input
     * @throws CalculatorException  if the operation fails
     * @throws NullPointerException if the input is empty or null
     * @throws ParseException       if the input can not be parsed into either double number or operator
     */
    public void evaluate(String input) throws CalculatorException, NullPointerException, ParseException {
        if (null == input || input == "") {
            throw new NullPointerException("input could not be null");
        }
        int index = 0;
        String[] split = input.toLowerCase().split(" ");
        for (String s : split) {
            index++;
            OperatorUtil operatorUtil = OperatorUtil.getEnum(s);
            if (null == operatorUtil) {
                Double real = DoubleUtil.tryParseDouble(s);
                if (null == real) {
                    throw new ParseException("can not parse input", index);
                } else {
                    current.push(real);
                }
            } else {
                process(operatorUtil, index);
            }
        }
    }

    /**
     * do a single oparetion
     *
     * @param operatorUtil the operation to process
     * @param index        the position of the operator
     * @throws CalculatorException if the operation fails
     * @throws ParseException      if the input can not be parsed into either double number or operator
     */
    private void process(OperatorUtil operatorUtil, int index) throws CalculatorException, ParseException {
        if (current.size() >= operatorUtil.getOperateNumber()) {
            if (OperatorUtil.CLEAR == operatorUtil) {
                clearStacks();
                return;
            }

            // undo evaluates the last instruction in stack
            if (OperatorUtil.UNDO == operatorUtil) {
                undoFlag = true;
                undoLastInstruction(index);
                return;
            }
            Double secondOperand = current.pop();
            Double firstOperand = (operatorUtil.getOperateNumber() > 1) ? current.pop() : null;
            // calculate
            Double result = operatorUtil.calculate(firstOperand, secondOperand);

            if (result != null) {
                current.push(result);
                if (!undoFlag) {
                    history.push(new Instruction(OperatorUtil.getEnum(operatorUtil.toString()), secondOperand));
                } else {
                    undoFlag = false;
                }
            }
        } else {
            String exception = String.format("operator %s (position: %d): insufficient parameters", operatorUtil.getSymbol(), index * 2 - 1);
            throw new CalculatorException(exception);
        }
    }

    /**
     * clear the current stack
     */
    public void clearStacks() {
        current.clear();
        history.clear();
    }

    /**
     * if the previous input is an operation, undo the operation. if the previous input is an number, undo the number. else throw exception
     *
     * @param index the position of the undo in the input
     */
    private void undoLastInstruction(int index) throws CalculatorException, ParseException {
        if (history.isEmpty() && current.isEmpty()) {
            String exception = String.format("operator %s (position: %d): insufficient parameters", OperatorUtil.UNDO.getSymbol(), index * 2 - 1);
            throw new CalculatorException(exception);
        }
        if (history.isEmpty()) {
            current.pop();
        } else {
            Instruction lastInstruction = history.pop();
            if (null == lastInstruction) {
                current.pop();
            } else {
                evaluate(lastInstruction.getReverseInstruction());
            }
        }
    }

    /**
     * if the previous input is an operation, undo the operation. if the previous input is an number, undo the number. else throw exception
     *
     * @return the well formatted numbers in the stack
     */
    public String getCurrentStack() {
        DecimalFormat fmt = new DecimalFormat("0.##########");
        fmt.setRoundingMode(RoundingMode.FLOOR);
        StringBuilder result = new StringBuilder("stack: ");
        for (Double value : current) {
            result.append(fmt.format(value)).append(" ");
        }
        return result.toString().trim();
    }

    /**
     * if the previous input is an operation, undo the operation. if the previous input is an number, undo the number. else throw exception
     *
     * @param inputExpression one line of the numbers and the operators input
     * @return the result after processing the input
     */
    public String getCurrentResultWithExpression(String inputExpression) {
        try {
            evaluate(inputExpression);
        } catch (Exception e) {
            return e.getMessage() + "\n" + getCurrentStack();
        }
        return getCurrentStack();
    }
}
