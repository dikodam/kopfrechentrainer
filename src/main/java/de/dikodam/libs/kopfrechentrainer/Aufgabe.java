package de.dikodam.libs.kopfrechentrainer;

import java.util.function.BinaryOperator;

public class Aufgabe {

    private final int erstesArgument;
    private final int zweitesArgument;
    private final BinaryOperator<Integer> operator;
    private Integer gerateneLoesung;

    public Aufgabe(int erstesArgument, int zweitesArgument, BinaryOperator<Integer> operator) {
        this.erstesArgument = erstesArgument;
        this.zweitesArgument = zweitesArgument;
        this.operator = operator;
        gerateneLoesung = null;
    }

    // TODO nur einmal raten?
    public boolean rate(int loesung) {
        if (gerateneLoesung != null) {
            throw new IllegalStateException("Lösung darf nur einmal geraten werden!");
        }
        gerateneLoesung = loesung;
        return korrektGeraten();
    }

    public boolean wurdeGeraten() {
        return gerateneLoesung != null;
    }

    public boolean korrektGeraten() {
        return operator.apply(erstesArgument, zweitesArgument).equals(gerateneLoesung);
    }

}
