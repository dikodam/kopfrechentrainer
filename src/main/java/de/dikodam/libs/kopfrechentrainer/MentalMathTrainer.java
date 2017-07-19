package de.dikodam.libs.kopfrechentrainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static de.dikodam.libs.kopfrechentrainer.ArithmeticOperation.*;

public class MentalMathTrainer {

    private Map<ArithmeticOperation, Boolean> eligibleOperations;

    private int minDigits1;
    private int minDigits2;
    private int maxDigits1;
    private int maxDigits2;

    public MentalMathTrainer() {
        minDigits1 = 1;
        minDigits2 = 1;
        maxDigits1 = 2;
        maxDigits2 = 2;

        eligibleOperations = new HashMap<>();
        eligibleOperations.put(ADDITION, true);
        eligibleOperations.put(SUBTRACTION, false);
        eligibleOperations.put(MULTIPLICATION, false);
        eligibleOperations.put(DIVISION, false);
    }

    public int getMinDigits1() {
        return minDigits1;
    }

    /**
     * @param newMinDigits1 neue minimale Stellenanzahl für 1. Argument
     * @throws IllegalArgumentException für minStellenanzahl1 <= 0
     */
    public void setMinDigits1(int newMinDigits1) {
        if (newMinDigits1 <= 0) {
            throw new IllegalArgumentException("Digits minimum for argument 1 has to be at least 1!");
        }
        this.minDigits1 = newMinDigits1;
        if (maxDigits1 < newMinDigits1) {
            maxDigits1 = newMinDigits1;
        }
    }

    public int getMinDigits2() {
        return minDigits2;
    }

    /**
     * @param newMinDigits2 neue minimale Stellenanzahl für 2. Argument
     * @throws IllegalArgumentException für newMinDigits2 <= 0
     */
    public void setMinDigits2(int newMinDigits2) {
        if (newMinDigits2 <= 0) {
            throw new IllegalArgumentException("Digits minimum for argument 2 has to be at least 1!");
        }
        this.minDigits2 = newMinDigits2;
        if (maxDigits2 < newMinDigits2) {
            maxDigits2 = newMinDigits2;
        }
    }

    public int getMaxDigits1() {
        return maxDigits1;
    }

    /**
     * @param newMaxDigits1 neue maximale Stellenanzahl für 1. Argument
     * @throws IllegalArgumentException für newMaxDigits1 <= 0
     */
    public void setMaxDigits1(int newMaxDigits1) {
        if (newMaxDigits1 <= 0) {
            throw new IllegalArgumentException("Digits maximum for argument 1 has to be at least 1!");
        }

        this.maxDigits1 = newMaxDigits1;

        if (minDigits1 > newMaxDigits1) {
            minDigits1 = newMaxDigits1;
        }
    }

    public int getMaxDigits2() {
        return maxDigits2;
    }

    /**
     * @param newMaxDigits2 neue maximale Stellenanzahl für 2. Argument
     * @throws IllegalArgumentException wenn maxStellenanzahl <= 0
     */
    public void setMaxDigits2(int newMaxDigits2) {
        if (newMaxDigits2 <= 0) {
            throw new IllegalArgumentException("Digits maximum for argument 2 has to be at least 1!");
        }

        this.maxDigits2 = newMaxDigits2;

        if (minDigits2 > newMaxDigits2) {
            minDigits2 = newMaxDigits2;
        }
    }

    public boolean isAdditionEligible() {
        return eligibleOperations.get(ADDITION);
    }

    public void setAdditionEligible(boolean additionEligible) {
        eligibleOperations.put(ADDITION, additionEligible);
    }

    public boolean isSubtractionEligible() {
        return eligibleOperations.get(SUBTRACTION);
    }

    public void setSubtraktionEligible(boolean subtraktionEligible) {
        eligibleOperations.put(SUBTRACTION, subtraktionEligible);
    }

    public boolean isMultiplicationEligible() {
        return eligibleOperations.get(MULTIPLICATION);
    }

    public void setMultiplikationEligible(boolean multiplikationEligible) {
        eligibleOperations.put(MULTIPLICATION, multiplikationEligible);
    }

    public boolean isDivisionEligible() {
        return eligibleOperations.get(DIVISION);
    }

    public void setDivisionEligible(boolean divisionEligible) {
        eligibleOperations.put(DIVISION, divisionEligible);
    }

    public Task generateTask() {
        // TODO nur ganzzahlige division
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int firstArgument = rand.nextInt((int) Math.pow(10, minDigits1 - 1), (int) Math.pow(10, maxDigits1));
        int secondArgument = rand.nextInt((int) Math.pow(10, minDigits2 - 1), (int) Math.pow(10, maxDigits2));
        return new Task(firstArgument, secondArgument, getRandomOperation());
    }

    private ArithmeticOperation getRandomOperation() {
        List<ArithmeticOperation> eligibleOps = eligibleOperations
            .entrySet()
            .stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        if (eligibleOps.isEmpty()) {
            throw new IllegalStateException("No arithmetic operations are set to eligible!");
        }
        return eligibleOps.get(ThreadLocalRandom.current()
            .nextInt(eligibleOps.size()));
    }

}
