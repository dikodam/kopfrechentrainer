package de.dikodam.libs.kopfrechentrainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.dikodam.libs.kopfrechentrainer.ArithmeticOperation.*;

public class MentalMathTrainer {

    private Map<ArithmeticOperation, Boolean> enabledOperations;

    private int minDigits1;
    private int minDigits2;
    private int maxDigits1;
    private int maxDigits2;

    public MentalMathTrainer() {
        minDigits1 = 1;
        minDigits2 = 1;
        maxDigits1 = 2;
        maxDigits2 = 2;

        enabledOperations = new HashMap<>();
        enabledOperations.put(ADDITION, true);
        enabledOperations.put(SUBTRACTION, false);
        enabledOperations.put(MULTIPLICATION, false);
        enabledOperations.put(DIVISION, false);
    }

    public int getMinDigits1() {
        return minDigits1;
    }

    /**
     * @param newMinDigits1 neue minimale Stellenanzahl für 1. Argument
     * @throws IllegalArgumentException für minStellenanzahl1 <= 0
     */
    public MentalMathTrainer setMinDigits1(int newMinDigits1) {
        if (newMinDigits1 <= 0) {
            throw new IllegalArgumentException("Digits minimum for argument 1 has to be at least 1!");
        }
        this.minDigits1 = newMinDigits1;
        if (maxDigits1 < newMinDigits1) {
            maxDigits1 = newMinDigits1;
        }
        return this;
    }

    public int getMinDigits2() {
        return minDigits2;
    }

    /**
     * @param newMinDigits2 neue minimale Stellenanzahl für 2. Argument
     * @throws IllegalArgumentException für newMinDigits2 <= 0
     */
    public MentalMathTrainer setMinDigits2(int newMinDigits2) {
        if (newMinDigits2 <= 0) {
            throw new IllegalArgumentException("Digits minimum for argument 2 has to be at least 1!");
        }
        this.minDigits2 = newMinDigits2;
        if (maxDigits2 < newMinDigits2) {
            maxDigits2 = newMinDigits2;
        }
        return this;
    }

    public int getMaxDigits1() {
        return maxDigits1;
    }

    /**
     * @param newMaxDigits1 neue maximale Stellenanzahl für 1. Argument
     * @throws IllegalArgumentException für newMaxDigits1 <= 0
     */
    public MentalMathTrainer setMaxDigits1(int newMaxDigits1) {
        if (newMaxDigits1 <= 0) {
            throw new IllegalArgumentException("Digits maximum for argument 1 has to be at least 1!");
        }

        this.maxDigits1 = newMaxDigits1;

        if (minDigits1 > newMaxDigits1) {
            minDigits1 = newMaxDigits1;
        }
        return this;
    }

    public int getMaxDigits2() {
        return maxDigits2;
    }

    /**
     * @param newMaxDigits2 neue maximale Stellenanzahl für 2. Argument
     * @throws IllegalArgumentException wenn maxStellenanzahl <= 0
     */
    public MentalMathTrainer setMaxDigits2(int newMaxDigits2) {
        if (newMaxDigits2 <= 0) {
            throw new IllegalArgumentException("Digits maximum for argument 2 has to be at least 1!");
        }

        this.maxDigits2 = newMaxDigits2;

        if (minDigits2 > newMaxDigits2) {
            minDigits2 = newMaxDigits2;
        }
        return this;
    }

    public boolean isAdditionEnabled() {
        return enabledOperations.get(ADDITION);
    }

    public MentalMathTrainer setAdditionEnabled(boolean additionEnabled) {
        enabledOperations.put(ADDITION, additionEnabled);
        return this;
    }

    public boolean isSubtractionEnabled() {
        return enabledOperations.get(SUBTRACTION);
    }

    public MentalMathTrainer setSubtractionEnabled(boolean subtraktionEnabled) {
        enabledOperations.put(SUBTRACTION, subtraktionEnabled);
        return this;
    }

    public boolean isMultiplicationEnabled() {
        return enabledOperations.get(MULTIPLICATION);
    }

    public MentalMathTrainer setMultiplikationEnabled(boolean multiplikationEnabled) {
        enabledOperations.put(MULTIPLICATION, multiplikationEnabled);
        return this;
    }

    public boolean isDivisionEnabled() {
        return enabledOperations.get(DIVISION);
    }

    public MentalMathTrainer setDivisionEnabled(boolean divisionEnabled) {
        enabledOperations.put(DIVISION, divisionEnabled);
        return this;
    }

    // TODO choose new denominator if no nominator exists?
    public Task generateTask() {
        ArithmeticOperation randomOperation = getRandomOperation();

        int arg1LowerBound = (int) Math.pow(10, minDigits1 - 1);
        int arg1UpperBound = (int) Math.pow(10, maxDigits1);
        int arg2LowerBound = (int) Math.pow(10, minDigits2 - 1);
        int arg2UpperBound = (int) Math.pow(10, maxDigits2);

        int firstArgument;
        int secondArgument = generateArgumentBetween(arg2LowerBound, arg2UpperBound);

        if (randomOperation == ArithmeticOperation.DIVISION) {
            // numerator / denominator
            firstArgument = generateNumeratorFor(secondArgument, arg2UpperBound);
        } else {
            firstArgument = generateArgumentBetween(arg1LowerBound, arg1UpperBound);
        }

        return new Task(firstArgument, secondArgument, randomOperation);
    }

    private ArithmeticOperation getRandomOperation() {
        List<ArithmeticOperation> enabledOps = enabledOperations
            .entrySet()
            .stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        if (enabledOps.isEmpty()) {
            throw new IllegalStateException("No arithmetic operations are enabled!");
        }
        return enabledOps.get(ThreadLocalRandom.current().nextInt(enabledOps.size()));
    }

    private int generateArgumentBetween(int inclusiveLowerBound, int exclusiveUpperBound) {
        return ThreadLocalRandom.current().nextInt(inclusiveLowerBound, exclusiveUpperBound);
    }

    private int generateNumeratorFor(int denominator, int exclusiveUpperBound) {
        // eine zufällig wählen
        // wenn keine da: illegal argument ?
        List<Integer> numerators = IntStream.range(denominator + 1, exclusiveUpperBound)
            .filter((number) -> number % denominator == 0)
            .boxed()
            .collect(Collectors.toList());

        if (numerators.isEmpty()) {
            throw new IllegalArgumentException(String.format("The denominator %d has no multiples in the range ]%d; %d]!",
                denominator, denominator, exclusiveUpperBound - 1));
        }

        return numerators.get(ThreadLocalRandom.current().nextInt(numerators.size()));
    }

}
