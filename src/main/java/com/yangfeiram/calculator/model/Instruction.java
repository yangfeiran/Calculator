package com.yangfeiram.calculator.model;

import com.yangfeiram.calculator.exception.CalculatorException;
import com.yangfeiram.calculator.utils.OperatorUtil;
import lombok.AllArgsConstructor;

/**
 * Each operation model, containing the operator and the second operand
 */
@AllArgsConstructor
public class Instruction {
    private OperatorUtil operator;
    private Double value;

    public String getReverseInstruction() throws CalculatorException {
        if (operator.getOperateNumber() < 1)
            throw new CalculatorException(String.format("invalid operation for operator %s", operator.getSymbol()));

        return (operator.getOperateNumber() < 2) ?
                String.format("%s", operator.getOpposite()) :
                String.format("%f %s %f", value, operator.getOpposite(), value);
    }
}
