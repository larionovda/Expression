package com.larionov.epam.service;

import java.util.Stack;

public interface Calculator {
    String transferToRPN(String expr);

    String filingString(Stack<Character> stack, StringBuilder current);

    Stack<Character> priorityManager(Stack<Character> stack, StringBuilder current, int priority);

    boolean isNumber(String str);

    double calcRPN(String rpn);

    int getPriority(char token);

}
