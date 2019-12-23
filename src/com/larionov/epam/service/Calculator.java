package com.larionov.epam.service;

public interface Calculator {
    String transferToRPN(String expr);

    boolean isNumber(String str);

    void calcRPN(String rpn);

    int getPriority(char token);
}
