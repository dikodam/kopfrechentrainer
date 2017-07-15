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

public class KopfrechenTrainerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Tested
    private KopfrechenTrainer tested;

    @Test
    public void defaultConstructor() throws Exception {
        assertThat(tested.getMinStellenanzahl1(), is(1));
        assertThat(tested.getMinStellenanzahl2(), is(1));
        assertThat(tested.getMaxStellenanzahl1(), is(2));
        assertThat(tested.getMaxStellenanzahl2(), is(2));
        assertThat(tested.isAdditionErlaubt(), is(true));
        assertThat(tested.isSubtraktionErlaubt(), is(false));
        assertThat(tested.isMultiplikationErlaubt(), is(false));
        assertThat(tested.isDivisionErlaubt(), is(false));
    }

    private void testMinMaxStellenanzahlAendern(String nameMinFeld, String nameMaxFeld, int minStellenVorher, int maxStellenVorher,
                                                int neuerWert, Consumer<Integer> testcode, int minStellenNachher, int maxStellenNachher) {
        Deencapsulation.setField(tested, nameMinFeld, minStellenVorher);
        Deencapsulation.setField(tested, nameMaxFeld, maxStellenVorher);

        testcode.accept(neuerWert);

        Integer minStellenanzahl = Deencapsulation.getField(tested, nameMinFeld);
        Integer maxStellenanzahl = Deencapsulation.getField(tested, nameMaxFeld);

        assertThat(minStellenanzahl, is(minStellenNachher));
        assertThat(maxStellenanzahl, is(maxStellenNachher));
    }

    @Test
    public void setMinStellenanzahl1OhneMaxAnpassung() throws Exception {
        testMinMaxStellenanzahlAendern(
            "minStellenanzahl1",
            "maxStellenanzahl1",
            2,
            6,
            4,
            tested::setMinStellenanzahl1,
            4,
            6);
    }

    @Test
    public void setMinStellenanzahl1MitMaxAnpassung() throws Exception {
        testMinMaxStellenanzahlAendern(
            "minStellenanzahl1",
            "maxStellenanzahl1",
            1,
            2,
            5,
            tested::setMinStellenanzahl1,
            5,
            5);
    }

    @Test
    public void setMinStellenanzahl2OhneMaxAnpassung() throws Exception {
        testMinMaxStellenanzahlAendern(
            "minStellenanzahl2",
            "maxStellenanzahl2",
            3,
            6,
            4,
            tested::setMinStellenanzahl2,
            4,
            6);
    }

    @Test
    public void setMinStellenanzahl2MitMaxAnpassung() throws Exception {
        testMinMaxStellenanzahlAendern(
            "minStellenanzahl2",
            "maxStellenanzahl2",
            3,
            6,
            7,
            tested::setMinStellenanzahl2,
            7,
            7);
    }

    @Test
    public void setMinStellenanzahl1Auf0Exception() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Minimale Stellenanzahl (Argument 1) muss mindestens 1 sein!");

        tested.setMinStellenanzahl1(0);
    }

    @Test
    public void setMinStellenanzahl2Exception() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Minimale Stellenanzahl (Argument 2) muss mindestens 1 sein!");

        tested.setMinStellenanzahl2(0);
    }

    @Test
    public void setMaxStellenanzahl1OhneMinAnpassung() throws Exception {
        testMinMaxStellenanzahlAendern(
            "minStellenanzahl1",
            "maxStellenanzahl1",
            1,
            4,
            3,
            tested::setMaxStellenanzahl1,
            1,
            3);
    }

    @Test
    public void setMaxStellenanzahl1MitMinAnpassung() throws Exception {
        testMinMaxStellenanzahlAendern(
            "minStellenanzahl1",
            "maxStellenanzahl1",
            3,
            4,
            2,
            tested::setMaxStellenanzahl1,
            2,
            2);
    }

    @Test
    public void setMaxStellenanzahl1Auf0Exception() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Die maximale Stellenanzahl (Argument 1) muss mindestens 1 sein!");

        tested.setMaxStellenanzahl1(0);
    }

    @Test
    public void setMaxStellenanzahl2OhneMinAnpassung() throws Exception {
        testMinMaxStellenanzahlAendern(
            "minStellenanzahl2",
            "maxStellenanzahl2",
            2,
            5,
            3,
            tested::setMaxStellenanzahl2,
            2,
            3);
    }

    @Test
    public void setMaxStellenanzahl2MitMinAnpassung() throws Exception {
        testMinMaxStellenanzahlAendern(
            "minStellenanzahl2",
            "maxStellenanzahl2",
            4,
            6,
            3,
            tested::setMaxStellenanzahl2,
            3,
            3);
    }

    @Test
    public void setMaxStellenanzahl2Auf0Exception() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Die maximale Stellenanzahl (Argument 2) muss mindestens 1 sein!");

        tested.setMaxStellenanzahl2(0);
    }

    @Test
    public void setAdditionErlaubt() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> erlaubteOperationen = Deencapsulation.getField(tested, "erlaubteOperationen");

        tested.setAdditionErlaubt(true);
        assertThat(erlaubteOperationen.get(ADDITION), is(true));

        tested.setAdditionErlaubt(false);
        assertThat(erlaubteOperationen.get(ADDITION), is(false));
    }


    @Test
    public void setSubtraktionErlaubt() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> erlaubteOperationen = Deencapsulation.getField(tested, "erlaubteOperationen");

        tested.setSubtraktionErlaubt(true);
        assertThat(erlaubteOperationen.get(SUBTRAKTION), is(true));

        tested.setSubtraktionErlaubt(false);
        assertThat(erlaubteOperationen.get(SUBTRAKTION), is(false));
    }

    @Test
    public void setMultiplikationErlaubt() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> erlaubteOperationen = Deencapsulation.getField(tested, "erlaubteOperationen");
        tested.setMultiplikationErlaubt(true);
        assertThat(erlaubteOperationen.get(MULTIPLIKATION), is(true));

        tested.setMultiplikationErlaubt(false);
        assertThat(erlaubteOperationen.get(MULTIPLIKATION), is(false));
    }

    @Test
    public void setDivisionErlaubt() throws Exception {
        Map<BinaryOperator<Integer>, Boolean> erlaubteOperationen = Deencapsulation.getField(tested, "erlaubteOperationen");

        tested.setDivisionErlaubt(true);
        assertThat(erlaubteOperationen.get(DIVISION), is(true));

        tested.setDivisionErlaubt(false);
        assertThat(erlaubteOperationen.get(DIVISION), is(false));
    }

    @Test
    public void produceAufgabe() {
        new Expectations(tested) {
            {
                Deencapsulation.invoke(tested, "randomOperation");
                result = SUBTRAKTION;
            }
        };

        Deencapsulation.setField(tested, "minStellenanzahl1", 3);
        Deencapsulation.setField(tested, "maxStellenanzahl1", 4);
        Deencapsulation.setField(tested, "minStellenanzahl2", 2);
        Deencapsulation.setField(tested, "maxStellenanzahl2", 3);


        Aufgabe result = tested.produceAufgabe();
        Integer resultArg1 = Deencapsulation.getField(result, "erstesArgument");
        assertThat(resultArg1, allOf(is(greaterThanOrEqualTo(100)), is(lessThanOrEqualTo(9999))));

        Integer resultArg2 = Deencapsulation.getField(result, "zweitesArgument");
        assertThat(resultArg2, allOf(is(greaterThanOrEqualTo(10)), is(lessThanOrEqualTo(999))));

        ArithmeticOperation resultOperation = Deencapsulation.getField(result, "operator");
        assertThat(resultOperation, is(SUBTRAKTION));
    }

    @Test
    public void randomOperation() {
        Supplier<ArithmeticOperation> testedMethod = () -> Deencapsulation.invoke(tested, "randomOperation");
        Consumer<ArithmeticOperation> validateAdditionAndDivision = result -> assertThat(result,
            anyOf(is(ADDITION), is(DIVISION)));
        Consumer<ArithmeticOperation> validateAdditionAndSubtraktion = result -> assertThat(result,
            anyOf(is(ADDITION), is(SUBTRAKTION)));

        // noob toStrings of lambdas for debugging purposes :S
        /*
            System.out.println("ADDITION: " + ADDITION);
            System.out.println("SUBTRAKTION: " + SUBTRAKTION);
            System.out.println("MULTIPLIKATION: " + MULTIPLIKATION);
            System.out.println("DIVISION: " + DIVISION);
        */
        // 1. Test
        Map<ArithmeticOperation, Boolean> erlaubteOperationen = Deencapsulation.getField(tested, "erlaubteOperationen");
        erlaubteOperationen.clear();
        erlaubteOperationen.put(ADDITION, true);
        erlaubteOperationen.put(SUBTRAKTION, false);
        erlaubteOperationen.put(MULTIPLIKATION, false);
        erlaubteOperationen.put(DIVISION, true);

        Stream.generate(testedMethod)
            .limit(100)
            .forEach(validateAdditionAndDivision);

        // 2. Test
        erlaubteOperationen.clear();
        erlaubteOperationen.put(ADDITION, true);
        erlaubteOperationen.put(SUBTRAKTION, true);
        erlaubteOperationen.put(MULTIPLIKATION, false);
        erlaubteOperationen.put(DIVISION, false);

        Stream.generate(testedMethod)
            .limit(100)
            .forEach(validateAdditionAndSubtraktion);
    }

    @Test
    public void randomOperationKeineErlaubtException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Keine Rechenoperation erlaubt!");

        Map<ArithmeticOperation, Boolean> erlaubteOperationen = Deencapsulation.getField(tested, "erlaubteOperationen");
        erlaubteOperationen.clear();

        Deencapsulation.invoke(tested, "randomOperation");
    }

}