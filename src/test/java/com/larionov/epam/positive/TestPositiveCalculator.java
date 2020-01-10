package com.larionov.epam.positive;

import com.larionov.epam.service.Calculator;
import com.larionov.epam.service.impl.CalculatorImpl;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    void testGetPriority() {
        assertThat(calculator.getPriority('1'), is(0));
    }

    @Test
    void testIsNumber() {
        assertThat(calculator.isNumber("1"), is(true));
    }

}
