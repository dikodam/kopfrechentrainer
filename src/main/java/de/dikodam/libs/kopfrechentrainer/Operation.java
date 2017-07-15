package de.dikodam.libs.kopfrechentrainer;

import java.util.function.BinaryOperator;

public final class Operation {

    public static final BinaryOperator<Integer> ADDITION = (a, b) -> a + b;
    public static final BinaryOperator<Integer> SUBTRAKTION = (a, b) -> a - b;
    public static final BinaryOperator<Integer> MULTIPLIKATION = (a, b) -> a * b;
    public static final BinaryOperator<Integer> DIVISION = (a, b) -> a / b;

    private Operation() {
        // Werkzeugklasse
    }
}
