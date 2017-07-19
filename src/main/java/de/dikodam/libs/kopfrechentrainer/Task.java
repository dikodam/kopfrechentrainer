package de.dikodam.libs.kopfrechentrainer;

import static de.dikodam.libs.kopfrechentrainer.ArithmeticOperation.*;

public class Task {

    private final int firstArgument;
    private final int secondArgument;
    private final ArithmeticOperation operator;
    private Integer guessedResult;

    public Task(int firstArgument, int secondArgument, ArithmeticOperation operator) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
        this.operator = operator;
        guessedResult = null;
        // TODO compute actual result
    }

    public boolean guess(int result) {
        if (guessedResult != null) {
            throw new IllegalStateException("You're only allowed to guess once!");
        }
        guessedResult = result;
        return guessedCorrectly();
    }

    public int getFirstArgument() {
        return firstArgument;
    }

    public int getSecondArgument() {
        return secondArgument;
    }

    public ArithmeticOperation getOperator() {
        return operator;
    }

    public Integer getGuessedResult() {
        return guessedResult;
    }

    public boolean guessWasTried() {
        return guessedResult != null;
    }

    public boolean guessedCorrectly() {
        return operator.apply(firstArgument, secondArgument).equals(guessedResult);
    }

    @Override
    public String toString() {
        String operatorString = "??";
        if (operator == ADDITION) {
            operatorString = "+";
        } else if (operator == SUBTRACTION) {
            operatorString = "-";
        } else if (operator == MULTIPLICATION) {
            operatorString = "*";
        } else if (operator == DIVISION) {
            operatorString = "/";
        }
        return String.format("%s %s %s", firstArgument, operatorString, secondArgument);
    }
}
