package com.yangfeiram.calculator.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2017/8/4.
 */
public class OperatorUtilTest {
    @Test
    public void checkOperatorTest() {
        String s1 = "+";
        String s2 = "-";
        String s3 = "*";
        String s4 = "/";
        String s5 = "undo";
        String s6 = "sqrt";
        String s7 = "clear";
        String s8 = "no";
        assertTrue("+ check fail", OperatorUtil.getEnum(s1) != null);
        assertTrue("- check fail", OperatorUtil.getEnum(s2) != null);
        assertTrue("* check fail", OperatorUtil.getEnum(s3) != null);
        assertTrue("/ check fail", OperatorUtil.getEnum(s4) != null);
        assertTrue("undo check fail", OperatorUtil.getEnum(s5) != null);
        assertTrue("sqrt check fail", OperatorUtil.getEnum(s6) != null);
        assertTrue("clear check fail", OperatorUtil.getEnum(s7) != null);
        assertTrue("no check fail", OperatorUtil.getEnum(s8) == null);
    }
}
