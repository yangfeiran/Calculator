package com.yangfeiram.calculator.utils;

import com.yangfeiram.calculator.exception.CalculatorException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Operator check util and all the operation logicss
 */
@AllArgsConstructor
public enum OperatorUtil {

    ADDITION("+", "-", 2) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            return firstOperand + secondOperand;
        }
    },

    SUBTRACTION("-", "+", 2) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return firstOperand - secondOperand;
        }
    },

    MULTIPLICATION("*", "/", 2) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return firstOperand * secondOperand;
        }
    },

    DIVISION("/", "*", 2) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            if (0 == secondOperand)
                throw new CalculatorException("cannot divide by 0.");
            return firstOperand / secondOperand;
        }
    },

    SQUAREROOT("sqrt", "pow", 1) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            if (secondOperand < 0) {
                throw new CalculatorException("should be more than 0.");
            }
            return sqrt(secondOperand);
        }
    },

    POWER("pow", "sqrt", 1) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return pow(secondOperand, 2.0);
        }
    },

    UNDO("undo", null, 0) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            throw new CalculatorException("invalid operation");
        }
    },

    CLEAR("clear", null, 0) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            throw new CalculatorException("invalid operation");
        }
    };
    // using map for a constant LOOK_UP cost
    private static final Map<String, OperatorUtil> LOOK_UP = new HashMap<String, OperatorUtil>();

    static {
        for (OperatorUtil o : values()) {
            LOOK_UP.put(o.getSymbol(), o);
        }
    }

    @Getter
    private String symbol;
    @Getter
    private String opposite;
    @Getter
    private int operateNumber;

    public static OperatorUtil getEnum(String value) {
        return LOOK_UP.get(value);
    }

    public abstract Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException;

    @Override
    public String toString() {
        return symbol;
    }
}
