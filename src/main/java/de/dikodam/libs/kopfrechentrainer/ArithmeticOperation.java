package de.dikodam.libs.kopfrechentrainer;

import java.util.function.BinaryOperator;

// TODO abstract class?

@FunctionalInterface
public interface ArithmeticOperation extends BinaryOperator<Integer> {
    ArithmeticOperation ADDITION = (a, b) -> a + b;
    ArithmeticOperation SUBTRACTION = (a, b) -> a - b;
    ArithmeticOperation MULTIPLICATION = (a, b) -> a * b;
    ArithmeticOperation DIVISION = (a, b) -> a / b;
}