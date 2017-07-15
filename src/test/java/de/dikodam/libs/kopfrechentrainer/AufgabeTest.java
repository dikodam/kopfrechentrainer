package de.dikodam.libs.kopfrechentrainer;

import mockit.Deencapsulation;
import mockit.Tested;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.function.BinaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class AufgabeTest {

    @Tested
    private Aufgabe tested;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        tested = new Aufgabe(6, 3, Deencapsulation.getField(KopfrechenTrainer.class, "ADDITION"));
    }

    @Test
    public void constructor() throws Exception {
        Integer erstesArgument = Deencapsulation.getField(tested, "erstesArgument");
        Integer zweitesArgument = Deencapsulation.getField(tested, "zweitesArgument");
        BinaryOperator<Integer> operator = Deencapsulation.getField(tested, "operator");
        Integer gerateneLoesung = Deencapsulation.getField(tested, "gerateneLoesung");
        BinaryOperator<Integer> addition = Deencapsulation.getField(KopfrechenTrainer.class, "ADDITION");

        assertThat(erstesArgument, is(6));
        assertThat(zweitesArgument, is(3));
        assertThat(operator, is(addition));
        assertThat(gerateneLoesung, is(nullValue()));
    }

    @Test
    public void rateLoesung() {
        boolean result = tested.rate(9);
        Integer gerateneLoesung = Deencapsulation.getField(tested, "gerateneLoesung");

        assertThat(gerateneLoesung, is(9));
        assertThat(result, is(true));
    }

    @Test
    public void rateFalscheLoesung() {
        boolean result = tested.rate(8);
        Integer gerateneLoesung = Deencapsulation.getField(tested, "gerateneLoesung");

        assertThat(gerateneLoesung, is(8));
        assertThat(result, is(false));
    }

    @Test
    public void rateLoesungErneutException() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Lösung darf nur einmal geraten werden!");

        tested.rate(5);
        tested.rate(8);
    }

    @Test
    public void wurdeGeraten() {
        assertThat(tested.wurdeGeraten(), is(false));
        tested.rate(5);
        assertThat(tested.wurdeGeraten(), is(true));
    }

    @Test
    public void korrektGeraten() {
        assertThat(tested.korrektGeraten(), is(false));

        Deencapsulation.setField(tested, "gerateneLoesung", 9);

        assertThat(tested.korrektGeraten(), is(true));
    }

}