package de.dikodam.libs.kopfrechentrainer;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Tested;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static de.dikodam.libs.kopfrechentrainer.ArithmeticOperation.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MentalMathTrainerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Tested
    private MentalMathTrainer tested;

    @Test
    public void defaultConstructor() throws Exception {
        assertThat(tested.getMinDigits1(), is(1));
        assertThat(tested.getMinDigits2(), is(1));
        assertThat(tested.getMaxDigits1(), is(2));
        assertThat(tested.getMaxDigits2(), is(2));
        assertThat(tested.isAdditionEligible(), is(true));
        assertThat(tested.isSubtractionEligible(), is(false));
        assertThat(tested.isMultiplicationEligible(), is(false));
        assertThat(tested.isDivisionEligible(), is(false));
    }

    private void testChangeMinMaxDigits(String nameMinField, String nameMaxField, int minDigitsBefore, int maxDigitsBefore,
                                        int newValue, Consumer<Integer> testcode, int minDigitsAfter, int maxDigitsAfter) {
        Deencapsulation.setField(tested, nameMinField, minDigitsBefore);
        Deencapsulation.setField(tested, nameMaxField, maxDigitsBefore);

        testcode.accept(newValue);

        Integer minDigits = Deencapsulation.getField(tested, nameMinField);
        Integer maxDigits = Deencapsulation.getField(tested, nameMaxField);

        assertThat(minDigits, is(minDigitsAfter));
        assertThat(maxDigits, is(maxDigitsAfter));
    }

    @Test
    public void setMinDigits_1_NoMaxAdjustment() throws Exception {
        testChangeMinMaxDigits(
            "minDigits1",
            "maxDigits1",
            2,
            6,
            4,
            tested::setMinDigits1,
            4,
            6);
    }

    @Test
    public void setMinDigits_1_WithMaxAdjustment() throws Exception {
        testChangeMinMaxDigits(
            "minDigits1",
            "maxDigits1",
            1,
            2,
            5,
            tested::setMinDigits1,
            5,
            5);
    }

    @Test
    public void setMinDigits_2_WithoutMaxAdjustment() throws Exception {
        testChangeMinMaxDigits(
            "minDigits2",
            "maxDigits2",
            3,
            6,
            4,
            tested::setMinDigits2,
            4,
            6);
    }

    @Test
    public void setMinDigits_2_WithMaxAdjustment() throws Exception {
        testChangeMinMaxDigits(
            "minDigits2",
            "maxDigits2",
            3,
            6,
            7,
            tested::setMinDigits2,
            7,
            7);
    }

    @Test
    public void setMinDigits_1_To_0_Exception() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Digits minimum for argument 1 has to be at least 1!");

        tested.setMinDigits1(0);
    }

    @Test
    public void setMinDigits_2_Exception() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Digits minimum for argument 2 has to be at least 1!");

        tested.setMinDigits2(0);
    }

    @Test
    public void setMaxDigits_1_WithoutMinAdjustment() throws Exception {
        testChangeMinMaxDigits(
            "minDigits1",
            "maxDigits1",
            1,
            4,
            3,
            tested::setMaxDigits1,
            1,
            3);
    }

    @Test
    public void setMaxDigits_1_WithMinAdjustment() throws Exception {
        testChangeMinMaxDigits(
            "minDigits1",
            "maxDigits1",
            3,
            4,
            2,
            tested::setMaxDigits1,
            2,
            2);
    }

    @Test
    public void setMaxDigits_1_To_0_Exception() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Digits maximum for argument 1 has to be at least 1!");

        tested.setMaxDigits1(0);
    }

    @Test
    public void setMaxDigits_2_WithoutMinAdjustment() throws Exception {
        testChangeMinMaxDigits(
            "minDigits2",
            "maxDigits2",
            2,
            5,
            3,
            tested::setMaxDigits2,
            2,
            3);
    }

    @Test
    public void setMaxDigits_2_WithMinAdjustment() throws Exception {
        testChangeMinMaxDigits(
            "minDigits2",
            "maxDigits2",
            4,
            6,
            3,
            tested::setMaxDigits2,
            3,
            3);
    }

    @Test
    public void setMaxDigits_2_To_0_Exception() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Digits maximum for argument 2 has to be at least 1!");

        tested.setMaxDigits2(0);
    }

    @Test
    public void setAdditionEligible() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> eligibleeOperationen = Deencapsulation.getField(tested, "eligibleOperations");

        tested.setAdditionEligible(true);
        assertThat(eligibleeOperationen.get(ADDITION), is(true));

        tested.setAdditionEligible(false);
        assertThat(eligibleeOperationen.get(ADDITION), is(false));
    }


    @Test
    public void setSubtractionEligible() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> eligibleOperations = Deencapsulation.getField(tested, "eligibleOperations");

        tested.setSubtraktionEligible(true);
        assertThat(eligibleOperations.get(SUBTRACTION), is(true));

        tested.setSubtraktionEligible(false);
        assertThat(eligibleOperations.get(SUBTRACTION), is(false));
    }

    @Test
    public void setMultiplicationEligible() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> eligibleOperations = Deencapsulation.getField(tested, "eligibleOperations");
        tested.setMultiplikationEligible(true);
        assertThat(eligibleOperations.get(MULTIPLICATION), is(true));

        tested.setMultiplikationEligible(false);
        assertThat(eligibleOperations.get(MULTIPLICATION), is(false));
    }

    @Test
    public void setDivisionEligible() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> eligibleOperations = Deencapsulation.getField(tested, "eligibleOperations");

        tested.setDivisionEligible(true);
        assertThat(eligibleOperations.get(DIVISION), is(true));

        tested.setDivisionEligible(false);
        assertThat(eligibleOperations.get(DIVISION), is(false));
    }

    @Test
    public void generateTask() {
        new Expectations(tested) {
            {
                Deencapsulation.invoke(tested, "getRandomOperation");
                result = SUBTRACTION;
            }
        };

        Deencapsulation.setField(tested, "minDigits1", 3);
        Deencapsulation.setField(tested, "maxDigits1", 4);
        Deencapsulation.setField(tested, "minDigits2", 2);
        Deencapsulation.setField(tested, "maxDigits2", 3);


        Task result = tested.generateTask();
        Integer resultArg1 = Deencapsulation.getField(result, "firstArgument");
        assertThat(resultArg1, allOf(is(greaterThanOrEqualTo(100)), is(lessThanOrEqualTo(9999))));

        Integer resultArg2 = Deencapsulation.getField(result, "secondArgument");
        assertThat(resultArg2, allOf(is(greaterThanOrEqualTo(10)), is(lessThanOrEqualTo(999))));

        ArithmeticOperation resultOperation = Deencapsulation.getField(result, "operator");
        assertThat(resultOperation, is(SUBTRACTION));
    }

    @Test
    public void randomOperation() {
        Supplier<ArithmeticOperation> testedMethod = () -> Deencapsulation.invoke(tested, "getRandomOperation");
        Consumer<ArithmeticOperation> validateAdditionAndDivision = result -> assertThat(result,
            anyOf(is(ADDITION), is(DIVISION)));
        Consumer<ArithmeticOperation> validateAdditionAndSubtraktion = result -> assertThat(result,
            anyOf(is(ADDITION), is(SUBTRACTION)));

        // noob toStrings of lambdas for debugging purposes :S
        /*
            System.out.println("ADDITION: " + ADDITION);
            System.out.println("SUBTRACTION: " + SUBTRACTION);
            System.out.println("MULTIPLICATION: " + MULTIPLICATION);
            System.out.println("DIVISION: " + DIVISION);
        */
        // 1. Test
        Map<ArithmeticOperation, Boolean> eligibleOperations = Deencapsulation.getField(tested, "eligibleOperations");
        eligibleOperations.clear();
        eligibleOperations.put(ADDITION, true);
        eligibleOperations.put(SUBTRACTION, false);
        eligibleOperations.put(MULTIPLICATION, false);
        eligibleOperations.put(DIVISION, true);

        Stream.generate(testedMethod)
            .limit(100)
            .forEach(validateAdditionAndDivision);

        // 2. Test
        eligibleOperations.clear();
        eligibleOperations.put(ADDITION, true);
        eligibleOperations.put(SUBTRACTION, true);
        eligibleOperations.put(MULTIPLICATION, false);
        eligibleOperations.put(DIVISION, false);

        Stream.generate(testedMethod)
            .limit(100)
            .forEach(validateAdditionAndSubtraktion);
    }

    @Test
    public void randomOperationNoneEligibleException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("No arithmetic operations are set to eligible!");

        Map<ArithmeticOperation, Boolean> eligibleOperations = Deencapsulation.getField(tested, "eligibleOperations");
        eligibleOperations.clear();

        Deencapsulation.invoke(tested, "getRandomOperation");
    }

}