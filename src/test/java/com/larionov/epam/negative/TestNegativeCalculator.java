package com.larionov.epam.negative;

import com.larionov.epam.service.Calculator;
import com.larionov.epam.service.impl.CalculatorImpl;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNegativeCalculator {
    Calculator calculator = new CalculatorImpl();

    @Test
    void testTransferToRPN() {
        assertThat(calculator.transferToRPN("1 + ( 4 / 6 ) - 5"), not("1 4 6 / +"));
    }

    @Test
    void testCalcRPNDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calcRPN("4 4 0 / +"));
    }

    @Test
    void testCalcRPNInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calcRPN("4 4 ! +"));
    }
}
