package com.larionov.epam.service.impl;

import com.larionov.epam.service.Calculator;

import java.util.Stack;

public class CalculatorImpl implements Calculator {

    @Override
    public void calcRPN(String rpn) {
        String[] strings = rpn.split(" ");
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < strings.length; i++) {
            if (isNumber(strings[i])) {
                stack.push(Double.parseDouble(strings[i]));
            } else {
                double temp1 = stack.pop();
                double temp2 = stack.pop();
                switch (strings[i]) {
                    case "+":
                        stack.push(temp1 + temp2);
                        break;
                    case "-":
                        stack.push(temp2 - temp1);
                        break;
                    case "*":
                        stack.push(temp1 * temp2);
                        break;
                    case "/":
                        stack.push(temp2 / temp1);
                        break;
                }
            }
        }
        if (!stack.empty()) {
            System.out.println(stack.pop());
        } else {
            System.out.println("Произошла ошибка");
        }
    }

    @Override
    public String transferToRPN(String expr) {
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int priority;
        for (int i = 0; i < expr.length(); i++) {
            priority = getPriority(expr.charAt(i)); // определяем приоритет текущего символа из строки
            if (priority == 0) { // если встретилось обычное число
                current.append(expr.charAt(i)); // вставляем обычное число в стрингбилдер current
            } else if (priority == 1) { // если открывающаяся скобка, то сразу отправляем ее в стэк
                stack.push(expr.charAt(i));
            } else if (priority > 1) { // если встретился любой математический знак, то отправляем его в стэк
                current.append(' ');
                while (!stack.empty()) { // если стэк не пустой, то мы должны вытащить из него все знаки пока приоритет след знака в стэке не будет меньше приоритета текущего символа
                    if (getPriority(stack.peek()) >= priority) {
                        current.append(stack.pop());// математические знаки большего приорита перед текущим символом извлекаем из стека и переносим в строку current
                        current.append(" ");
                    } else {
                        break;
                    }
                }
                stack.push(expr.charAt(i)); // отправляем текущий символ в стэк
            } else if (priority == -1) { // если мы встретили закрывающуюся скобку
                current.append(' ');
                while (getPriority(stack.peek()) != 1) { // извлекаем знаки из стека до тех пор пока не встретим открывающуюся скобку
                    current.append(stack.pop());
                }
                stack.pop(); // извлекаем открывающуюся скобку
            }
        }
        while (!stack.empty()) { // извлекаем все оставшиеся мат знаки в стеке в строку current
            current.append(" ");
            current.append(stack.pop());
        }
        return current.toString();
    }

    @Override
    public int getPriority(char token) {
        switch (token) {
            case '*':
            case '/':
                return 3;
            case '+':
            case '-':
                return 2;
            case '(':
                return 1;
            case ')':
                return -1;
            default:
                return 0;
        }
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
