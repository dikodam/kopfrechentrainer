package de.dikodam.libs.kopfrechentrainer;

import mockit.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
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
        assertThat(tested.isAdditionEnabled(), is(true));
        assertThat(tested.isSubtractionEnabled(), is(false));
        assertThat(tested.isMultiplicationEnabled(), is(false));
        assertThat(tested.isDivisionEnabled(), is(false));
    }

    private void testSetter(String nameMinField, String nameMaxField, int minDigitsBefore, int maxDigitsBefore,
                            int newValue, Function<Integer, MentalMathTrainer> testcode, int minDigitsAfter, int maxDigitsAfter) {
        Deencapsulation.setField(tested, nameMinField, minDigitsBefore);
        Deencapsulation.setField(tested, nameMaxField, maxDigitsBefore);

        MentalMathTrainer returnedMMT = testcode.apply(newValue);

        Integer minDigits = Deencapsulation.getField(tested, nameMinField);
        Integer maxDigits = Deencapsulation.getField(tested, nameMaxField);

        assertThat(minDigits, is(minDigitsAfter));
        assertThat(maxDigits, is(maxDigitsAfter));

        assertThat(returnedMMT, is(tested));
    }

    @Test
    public void setMinDigits_1_NoMaxAdjustment() throws Exception {
        testSetter(
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
        testSetter(
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
        testSetter(
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
        testSetter(
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
        testSetter(
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
        testSetter(
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
        testSetter(
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
        testSetter(
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
    public void setAdditionEnabled() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> enabledeOperationen = Deencapsulation.getField(tested, "enabledOperations");

        MentalMathTrainer returnedMMT = tested.setAdditionEnabled(true);
        assertThat(enabledeOperationen.get(ADDITION), is(true));
        assertThat(returnedMMT, is(tested));

        returnedMMT = tested.setAdditionEnabled(false);
        assertThat(enabledeOperationen.get(ADDITION), is(false));
        assertThat(returnedMMT, is(tested));
    }


    @Test
    public void setSubtractionEnabled() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> enabledOperations = Deencapsulation.getField(tested, "enabledOperations");

        tested.setSubtractionEnabled(true);
        assertThat(enabledOperations.get(SUBTRACTION), is(true));

        tested.setSubtractionEnabled(false);
        assertThat(enabledOperations.get(SUBTRACTION), is(false));
    }

    @Test
    public void setMultiplicationEnabled() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> enabledOperations = Deencapsulation.getField(tested, "enabledOperations");
        tested.setMultiplikationEnabled(true);
        assertThat(enabledOperations.get(MULTIPLICATION), is(true));

        tested.setMultiplikationEnabled(false);
        assertThat(enabledOperations.get(MULTIPLICATION), is(false));
    }

    @Test
    public void setDivisionEnabled() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> enabledOperations = Deencapsulation.getField(tested, "enabledOperations");

        tested.setDivisionEnabled(true);
        assertThat(enabledOperations.get(DIVISION), is(true));

        tested.setDivisionEnabled(false);
        assertThat(enabledOperations.get(DIVISION), is(false));
    }

    @Test
    public void generateTask() {

        Deencapsulation.setField(tested, "minDigits1", 3);
        Deencapsulation.setField(tested, "maxDigits1", 4);
        Deencapsulation.setField(tested, "minDigits2", 2);
        Deencapsulation.setField(tested, "maxDigits2", 3);

        new MockUp<MentalMathTrainer>() {
            @Mock
            private ArithmeticOperation getRandomOperation() {
                return SUBTRACTION;
            }

            @Mock
            private int generateArgumentBetween(int inclusiveLowerBound, int exclusiveUpperBound) {
                if (inclusiveLowerBound == 10 && exclusiveUpperBound == 1000) {
                    return 125;
                }
                if (inclusiveLowerBound == 100 && exclusiveUpperBound == 10000) {
                    return 568;
                }
                throw new AssertionError(String.format(
                    "Unexpected method call with parameters %d and %d", inclusiveLowerBound, exclusiveUpperBound));
            }
        };


        Task result = tested.generateTask();
        Integer resultArg1 = Deencapsulation.getField(result, "firstArgument");
        assertThat(resultArg1, is(568));

        Integer resultArg2 = Deencapsulation.getField(result, "secondArgument");
        assertThat(resultArg2, is(125));

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
        Map<ArithmeticOperation, Boolean> enabledOperations = Deencapsulation.getField(tested, "enabledOperations");
        enabledOperations.clear();
        enabledOperations.put(ADDITION, true);
        enabledOperations.put(SUBTRACTION, false);
        enabledOperations.put(MULTIPLICATION, false);
        enabledOperations.put(DIVISION, true);

        Stream.generate(testedMethod)
            .limit(100)
            .forEach(validateAdditionAndDivision);

        // 2. Test
        enabledOperations.clear();
        enabledOperations.put(ADDITION, true);
        enabledOperations.put(SUBTRACTION, true);
        enabledOperations.put(MULTIPLICATION, false);
        enabledOperations.put(DIVISION, false);

        Stream.generate(testedMethod)
            .limit(100)
            .forEach(validateAdditionAndSubtraktion);
    }

    @Test
    public void randomOperationNoneEnabledException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("No arithmetic operations are enabled!");

        Map<ArithmeticOperation, Boolean> enabledOperations = Deencapsulation.getField(tested, "enabledOperations");
        enabledOperations.clear();

        Deencapsulation.invoke(tested, "getRandomOperation");
    }

    @Test
    public void generateNumberForDenominator() throws Exception {
        int result = Deencapsulation.invoke(tested, "generateNumeratorFor", 2, 11);

        assertThat(result, anyOf(is(4), is(6), is(8), is(10)));
    }

    @Test
    public void generateNumberForDenominatorNoValidException() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("The denominator 245 has no multiples in the range ]245; 299]!");

        Integer result = Deencapsulation.invoke(tested, "generateNumeratorFor", 245, 300);
        System.out.println(result == null);
    }

    @Test
    public void getTaks() {
        List<Task> result = tested.generateTasks(200);

        assertThat(result.size(), is(200));
    }

    @Test
    public void getTaksIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Impossible to generate 0 tasks!");

        tested.generateTasks(0);
    }
}