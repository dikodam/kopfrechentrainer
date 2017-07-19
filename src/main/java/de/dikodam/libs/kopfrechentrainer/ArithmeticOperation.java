package de.dikodam.libs.kopfrechentrainer;

import java.util.function.BinaryOperator;

@FunctionalInterface
public interface ArithmeticOperation extends BinaryOperator<Integer> {
    ArithmeticOperation ADDITION = (a, b) -> a + b;
    ArithmeticOperation SUBTRACTION = (a, b) -> a - b;
    ArithmeticOperation MULTIPLICATION = (a, b) -> a * b;
    ArithmeticOperation DIVISION = (a, b) -> a / b;

    default String print(int a, int b) {
        String operatorString = "?";
        if (this == ADDITION) {
            operatorString = "+";
        } else if (this == SUBTRACTION) {
            operatorString = "-";
        } else if (this == MULTIPLICATION) {
            operatorString = "*";
        } else if (this == DIVISION) {
            operatorString = "/";
        }
        return String.format("%d %s %d", a, operatorString, b);
    }

}