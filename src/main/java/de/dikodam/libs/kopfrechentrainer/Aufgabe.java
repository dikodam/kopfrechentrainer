package de.dikodam.libs.kopfrechentrainer;

public class Aufgabe {

    private final int erstesArgument;
    private final int zweitesArgument;
    private final ArithmeticOperation operator;
    private Integer gerateneLoesung;

    public Aufgabe(int erstesArgument, int zweitesArgument, ArithmeticOperation operator) {
        this.erstesArgument = erstesArgument;
        this.zweitesArgument = zweitesArgument;
        this.operator = operator;
        gerateneLoesung = null;
    }

    public boolean rate(int loesung) {
        if (gerateneLoesung != null) {
            throw new IllegalStateException("Lösung darf nur einmal geraten werden!");
        }
        gerateneLoesung = loesung;
        return korrektGeraten();
    }

    public int getErstesArgument() {
        return erstesArgument;
    }

    public int getZweitesArgument() {
        return zweitesArgument;
    }

    public ArithmeticOperation getOperator() {
        return operator;
    }

    public Integer getGerateneLoesung() {
        return gerateneLoesung;
    }

    public boolean wurdeGeraten() {
        return gerateneLoesung != null;
    }

    public boolean korrektGeraten() {
        return operator.apply(erstesArgument, zweitesArgument).equals(gerateneLoesung);
    }

    @Override
    public String toString() {
        String operatorString = "??";
        if (operator == ArithmeticOperation.ADDITION) {
            operatorString = "+";
        } else if (operator == ArithmeticOperation.SUBTRAKTION) {
            operatorString = "-";
        } else if (operator == ArithmeticOperation.MULTIPLIKATION) {
            operatorString = "*";
        } else if (operator == ArithmeticOperation.DIVISION) {
            operatorString = "/";
        }
        return String.format("%s %s %s", erstesArgument, operatorString, zweitesArgument);
    }
}
