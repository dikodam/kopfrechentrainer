package de.dikodam.libs.kopfrechentrainer;

import java.util.function.BinaryOperator;

public interface ArithmeticOperation extends BinaryOperator<Integer> {
    ArithmeticOperation ADDITION = (a, b) -> a + b;
    ArithmeticOperation SUBTRAKTION = (a, b) -> a - b;
    ArithmeticOperation MULTIPLIKATION = (a, b) -> a * b;
    ArithmeticOperation DIVISION = (a, b) -> a / b;
}