package de.dikodam.libs.kopfrechentrainer;

import java.util.function.BinaryOperator;

public class KopfrechenTrainer {

    private static final BinaryOperator<Integer> ADDITION = (a, b) -> a + b;
    private static final BinaryOperator<Integer> SUBTRAKTION = (a, b) -> a - b;
    private static final BinaryOperator<Integer> MULTIPLIKATION = (a, b) -> a * b;
    private static final BinaryOperator<Integer> DIVISION = (a, b) -> a / b;

    private int minStellenanzahl1;
    private int minStellenanzahl2;
    private int maxStellenanzahl1;
    private int maxStellenanzahl2;

    private boolean additionRechnen;
    private boolean subtraktionRechnen;
    private boolean multiplikationRechnen;
    private boolean divisionRechnen;


    public KopfrechenTrainer() {
        minStellenanzahl1 = 1;
        minStellenanzahl2 = 1;
        maxStellenanzahl1 = 2;
        maxStellenanzahl2 = 2;

        additionRechnen = true;
        subtraktionRechnen = false;
        multiplikationRechnen = false;
        divisionRechnen = false;
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

    public boolean isAdditionRechnen() {
        return additionRechnen;
    }

    public void setAdditionRechnen(boolean additionRechnen) {
        this.additionRechnen = additionRechnen;
    }

    public boolean isSubtraktionRechnen() {
        return subtraktionRechnen;
    }

    public void setSubtraktionRechnen(boolean subtraktionRechnen) {
        this.subtraktionRechnen = subtraktionRechnen;
    }

    public boolean isMultiplikationRechnen() {
        return multiplikationRechnen;
    }

    public void setMultiplikationRechnen(boolean multiplikationRechnen) {
        this.multiplikationRechnen = multiplikationRechnen;
    }

    public boolean isDivisionRechnen() {
        return divisionRechnen;
    }

    public void setDivisionRechnen(boolean divisionRechnen) {
        this.divisionRechnen = divisionRechnen;
    }


}
