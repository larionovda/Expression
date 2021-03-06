package com.larionov.epam.service.impl;

import com.larionov.epam.service.Calculator;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorImpl implements Calculator {

    @Override
    public String transferToRPN(String expr) {
        String newExpr = expr.replaceAll("\\s+", "");
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int priority;
        for (int i = 0; i < newExpr.length(); i++) {
            priority = getPriority(newExpr.charAt(i)); // определяем приоритет текущего символа из строки
            if (priority == 0) { // если встретилось обычное число
                current.append(newExpr.charAt(i)); // вставляем обычное число в стрингбилдер current
            } else if (priority == 1) { // если открывающаяся скобка, то сразу отправляем ее в стэк
                stack.push(newExpr.charAt(i));
            } else if (priority > 1) { // если встретился любой математический знак, то отправляем его в стэк
                current.append(' ');
                priorityManager(stack, current, priority).push(newExpr.charAt(i)); // отправляем текущий символ в стэк
            } else if (priority == -1) { // если мы встретили закрывающуюся скобку
                current.append(' ');
                while (getPriority(stack.peek()) != 1) { // извлекаем знаки из стека до тех пор пока не встретим открывающуюся скобку
                    current.append(stack.pop());
                }
                stack.pop(); // извлекаем открывающуюся скобку
            }
        }
        return filingString(stack, current);
    }

    @Override
    public String filingString(Stack<Character> stack, StringBuilder current) {
        while (!stack.empty()) { // извлекаем все оставшиеся мат знаки в стеке в строку current
            current.append(" ");
            current.append(stack.pop());
        }
        return current.toString();
    }

    @Override
    public Stack<Character> priorityManager(Stack<Character> stack, StringBuilder current, int priority) {
        while (!stack.empty()) { // если стэк не пустой, то мы должны вытащить из него все знаки пока приоритет след знака в стэке не будет меньше приоритета текущего символа
            if (getPriority(stack.peek()) >= priority) {
                current.append(stack.pop());// математические знаки большего приорита перед текущим символом извлекаем из стека и переносим в строку current
                current.append(" ");
            } else {
                break;
            }
        }
        return stack;
    }


    @Override
    public int getPriority(char token) {
        switch (token) {
            case '*':
            case '/':
                return Operations.MULTIPLY.getPriority();
            case '+':
            case '-':
                return Operations.PLUS.getPriority();
            case '(':
                return Operations.OPEN_BRACKET.getPriority();
            case ')':
                return Operations.CLOSE_BRACKET.getPriority();
            default:
                return Operations.NUMBER.getPriority();
        }
    }

    @Override
    public double calcRPN(String rpn) {
        Pattern pattern = Pattern.compile("(0 /|[!@#$%^&<>,.?|№`~{}:;])");
        Matcher matcher = pattern.matcher(rpn);
        if (matcher.find()) {
            throw new IllegalArgumentException();
        }
        String[] strings = rpn.split(" ");
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < strings.length; i++) {
            if (isNumber(strings[i])) {
                stack.push(Double.parseDouble(strings[i]));
            } else {
                try {
                    double temp1 = stack.pop();
                    double temp2 = stack.pop();
                    switch (strings[i]) {
                        case "+":
                            stack.push(Operations.PLUS.action(temp1, temp2));
                            break;
                        case "-":
                            stack.push(Operations.MINUS.action(temp2, temp1));
                            break;
                        case "*":
                            stack.push(Operations.MULTIPLY.action(temp1, temp2));
                            break;
                        case "/":
                            stack.push(Operations.DIVISION.action(temp2, temp1));
                            break;
                    }
                } catch (EmptyStackException e) {
                    System.out.println("Введены некорректные данные");
                }
            }
        }
        if (!stack.empty()) {
            return stack.pop();
        } else {
            System.out.println("Произошла ошибка");
        }
        return 0.0;
    }

    @Override
    public boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
