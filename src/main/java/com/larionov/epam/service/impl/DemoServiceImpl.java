package com.larionov.epam.service.impl;

import com.larionov.epam.service.Calculator;
import com.larionov.epam.service.DemoService;

public class DemoServiceImpl implements DemoService {

    Calculator calculator = new CalculatorImpl();

    @Override
    public void service(String string) {
        System.out.println(calculator.calcRPN(calculator.transferToRPN(string)));
    }
}
