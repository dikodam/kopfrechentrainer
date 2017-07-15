package de.dikodam.libs.kopfrechentrainer;

import mockit.Deencapsulation;
import mockit.Tested;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static de.dikodam.libs.kopfrechentrainer.ArithmeticOperation.*;
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
        tested = new Aufgabe(6, 3, ADDITION);
    }

    @Test
    public void constructorAndGetters() throws Exception {

        assertThat(tested.getErstesArgument(), is(6));
        assertThat(tested.getZweitesArgument(), is(3));
        assertThat(tested.getOperator(), is(ArithmeticOperation.ADDITION));
        // TODO wirklich public?
        assertThat(tested.getGerateneLoesung(), is(nullValue()));
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

    @Test
    public void testToString() {
        assertThat(tested.toString(), is("6 + 3"));
        assertThat(new Aufgabe(5, 8, SUBTRAKTION).toString(), is("5 - 8"));
        assertThat(new Aufgabe(2, 3, MULTIPLIKATION).toString(), is("2 * 3"));
        assertThat(new Aufgabe(2, 5, DIVISION).toString(), is("2 / 5"));
    }

}