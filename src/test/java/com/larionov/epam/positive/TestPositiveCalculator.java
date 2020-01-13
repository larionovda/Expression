package com.larionov.epam.positive;

import com.larionov.epam.service.Calculator;
import com.larionov.epam.service.impl.CalculatorImpl;
import com.larionov.epam.service.impl.Operations;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPositiveCalculator {

    Calculator calculator = new CalculatorImpl();

    @Test
    void testTransferToRPN() {
        assertThat(calculator.transferToRPN("1 + ( 4 / 6 )"), is("1 4 6 / +"));
    }

    @Test
    void testFilingString() {
        Stack<Character> stack = new Stack<>();
        assertThat(calculator.filingString(stack, new StringBuilder().append("1 4 6 / +")), is("1 4 6 / +"));
    }

    @Test
    void testPriorityManager() {
        Stack<Character> param = new Stack<>();
        Stack<Character> expResult = new Stack<>();
        assertThat(calculator.priorityManager(param, new StringBuilder().append("1 "), 2), is(expResult));
    }

    @Test
    void testCalcRPN() {
        assertThat(calculator.calcRPN("36 4 2 + / 2 * 2 -"), is(10.0));
    }

    @Test
    void testGetPriority() {
        assertThat(calculator.getPriority('1'), is(0));
    }

    @Test
    void testIsNumber() {
        assertThat(calculator.isNumber("1"), is(true));
    }

    @Test
    void testNumber() {
        assertThat(Operations.NUMBER.action(2, 3), is(0.0));
    }

    @Test
    void testCloseBracket() {
        assertThat(Operations.CLOSE_BRACKET.action(2, 3), is(0.0));
    }

    @Test
    void testOpenBracket() {
        assertThat(Operations.OPEN_BRACKET.action(2, 3), is(0.0));
    }

    @Test
    void testMinus() {
        assertThat(Operations.MINUS.action(5, 3), is(2.0));
    }

    @Test
    void testPlus() {
        assertThat(Operations.PLUS.action(5, 3), is(8.0));
    }

    @Test
    void testMultiply() {
        assertThat(Operations.MULTIPLY.action(5, 3), is(15.0));
    }

    @Test
    void testDivision() {
        assertThat(Operations.DIVISION.action(8, 4), is(2.0));
    }


}
