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

public class TaskTest {

    @Tested
    private Task tested;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        tested = new Task(6, 3, ADDITION);
    }

    @Test
    public void constructorAndGetters() throws Exception {

        assertThat(tested.getFirstArgument(), is(6));
        assertThat(tested.getSecondArgument(), is(3));
        assertThat(tested.getOperator(), is(ArithmeticOperation.ADDITION));
        // TODO wirklich public?
        assertThat(tested.getGuessedResult(), is(nullValue()));
    }

    @Test
    public void guess() {
        boolean result = tested.guess(9);
        Integer guessedResult = Deencapsulation.getField(tested, "guessedResult");

        assertThat(guessedResult, is(9));
        assertThat(result, is(true));
    }

    @Test
    public void guessWrong() {
        boolean result = tested.guess(8);
        Integer guessedResult = Deencapsulation.getField(tested, "guessedResult");

        assertThat(guessedResult, is(8));
        assertThat(result, is(false));
    }

    @Test
    public void guessWasTried_and_MoreThanOneGuessException() {
        assertThat(tested.guessWasTried(), is(false));

        tested.guess(5);
        assertThat(tested.guessWasTried(), is(true));

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("You're only allowed to guess once!");

        tested.guess(8);
    }

    @Test
    public void guessedCorrectly() {
        assertThat(tested.guessedCorrectly(), is(false));

        Deencapsulation.setField(tested, "guessedResult", 9);

        assertThat(tested.guessedCorrectly(), is(true));
    }

    @Test
    public void testToString() {
        assertThat(new Task(6, 3, ADDITION).toString(), is("6 + 3"));
        assertThat(new Task(5, 8, SUBTRACTION).toString(), is("5 - 8"));
        assertThat(new Task(2, 3, MULTIPLICATION).toString(), is("2 * 3"));
        assertThat(new Task(2, 5, DIVISION).toString(), is("2 / 5"));
    }

}