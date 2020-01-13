package com.larionov.epam.service.impl;

public enum Operations {

    NUMBER(0) {
        @Override
        public double action(double x, double y) {
            return 0;
        }
    },
    CLOSE_BRACKET(-1) {
        @Override
        public double action(double x, double y) {
            return 0;
        }
    },
    OPEN_BRACKET(1) {
        @Override
        public double action(double x, double y) {
            return 0;
        }
    },
    PLUS(2) {
        public double action(double x, double y) {
            return x + y;
        }
    },
    MINUS(2) {
        public double action(double x, double y) {
            return x - y;
        }
    },
    DIVISION(3) {
        public double action(double x, double y) {
            return x / y;
        }
    },
    MULTIPLY(3) {
        public double action(double x, double y) {
            return x * y;
        }
    };

    public abstract double action(double x, double y);

    int priority;

    Operations(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
