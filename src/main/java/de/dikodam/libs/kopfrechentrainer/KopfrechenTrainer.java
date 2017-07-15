package de.dikodam.libs.kopfrechentrainer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

import static de.dikodam.libs.kopfrechentrainer.Operation.*;

public class KopfrechenTrainer {


    private static Map<BinaryOperator<Integer>, Boolean> erlaubteOperationen;

    private int minStellenanzahl1;
    private int minStellenanzahl2;
    private int maxStellenanzahl1;
    private int maxStellenanzahl2;

    public KopfrechenTrainer() {
        minStellenanzahl1 = 1;
        minStellenanzahl2 = 1;
        maxStellenanzahl1 = 2;
        maxStellenanzahl2 = 2;

        erlaubteOperationen = new HashMap<>();
        erlaubteOperationen.put(ADDITION, true);
        erlaubteOperationen.put(SUBTRAKTION, false);
        erlaubteOperationen.put(MULTIPLIKATION, false);
        erlaubteOperationen.put(DIVISION, false);
    }

    public int getMinStellenanzahl1() {
        return minStellenanzahl1;
    }

    /**
     * @param minStellenanzahl1 neue minimale Stellenanzahl für 1. Argument
     * @throws IllegalArgumentException für minStellenanzahl1 <= 0
     */
    public void setMinStellenanzahl1(int minStellenanzahl1) {
        if (minStellenanzahl1 <= 0) {
            throw new IllegalArgumentException("Minimale Stellenanzahl (Argument 1) muss mindestens 1 sein!");
        }
        this.minStellenanzahl1 = minStellenanzahl1;
        if (maxStellenanzahl1 < minStellenanzahl1) {
            maxStellenanzahl1 = minStellenanzahl1;
        }
    }

    public int getMinStellenanzahl2() {
        return minStellenanzahl2;
    }

    /**
     * @param minStellenanzahl2 neue minimale Stellenanzahl für 2. Argument
     * @throws IllegalArgumentException für minStellenanzahl2 <= 0
     */
    public void setMinStellenanzahl2(int minStellenanzahl2) {
        if (minStellenanzahl2 <= 0) {
            throw new IllegalArgumentException("Minimale Stellenanzahl (Argument 2) muss mindestens 1 sein!");
        }
        this.minStellenanzahl2 = minStellenanzahl2;
        if (maxStellenanzahl2 < minStellenanzahl2) {
            maxStellenanzahl2 = minStellenanzahl2;
        }
    }

    public int getMaxStellenanzahl1() {
        return maxStellenanzahl1;
    }

    /**
     * @param maxStellenanzahl1 neue maximale Stellenanzahl für 1. Argument
     * @throws IllegalArgumentException für maxStellenanzahl1 <= 0
     */
    public void setMaxStellenanzahl1(int maxStellenanzahl1) {
        if (maxStellenanzahl1 <= 0) {
            throw new IllegalArgumentException("Die maximale Stellenanzahl (Argument 1) muss mindestens 1 sein!");
        }

        this.maxStellenanzahl1 = maxStellenanzahl1;

        if (minStellenanzahl1 > maxStellenanzahl1) {
            minStellenanzahl1 = maxStellenanzahl1;
        }
    }

    public int getMaxStellenanzahl2() {
        return maxStellenanzahl2;
    }

    /**
     * @param maxStellenanzahl2 neue maximale Stellenanzahl für 2. Argument
     * @throws IllegalArgumentException wenn maxStellenanzahl <= 0
     */
    public void setMaxStellenanzahl2(int maxStellenanzahl2) {
        if (maxStellenanzahl2 <= 0) {
            throw new IllegalArgumentException("Die maximale Stellenanzahl (Argument 2) muss mindestens 1 sein!");
        }

        this.maxStellenanzahl2 = maxStellenanzahl2;

        if (minStellenanzahl2 > maxStellenanzahl2) {
            minStellenanzahl2 = maxStellenanzahl2;
        }
    }

    public boolean isAdditionErlaubt() {
        return erlaubteOperationen.get(ADDITION);
    }

    public void setAdditionErlaubt(boolean additionErlaubt) {
        erlaubteOperationen.put(ADDITION, additionErlaubt);
    }

    public boolean isSubtraktionErlaubt() {
        return erlaubteOperationen.get(SUBTRAKTION);
    }

    public void setSubtraktionErlaubt(boolean subtraktionErlaubt) {
        erlaubteOperationen.put(SUBTRAKTION, subtraktionErlaubt);
    }

    public boolean isMultiplikationErlaubt() {
        return erlaubteOperationen.get(MULTIPLIKATION);
    }

    public void setMultiplikationErlaubt(boolean multiplikationErlaubt) {
        erlaubteOperationen.put(MULTIPLIKATION, multiplikationErlaubt);
    }

    public boolean isDivisionErlaubt() {
        return erlaubteOperationen.get(DIVISION);
    }

    public void setDivisionErlaubt(boolean divisionErlaubt) {
        erlaubteOperationen.put(DIVISION, divisionErlaubt);
    }

    public Aufgabe produceAufgabe() {

        return new Aufgabe(1, 2, randomOperation());
    }

    private BinaryOperator<Integer> randomOperation() {

        return null;
    }

}
