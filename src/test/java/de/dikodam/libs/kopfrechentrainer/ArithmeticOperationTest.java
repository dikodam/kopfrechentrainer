package de.dikodam.libs.kopfrechentrainer;

import org.junit.Test;

import static de.dikodam.libs.kopfrechentrainer.ArithmeticOperation.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArithmeticOperationTest {

    @Test
    public void operationen() throws Exception {
        assertThat(ADDITION.apply(1, 2), is(3));
        assertThat(SUBTRAKTION.apply(1, 2), is(-1));
        assertThat(MULTIPLIKATION.apply(3, 4), is(12));
        assertThat(DIVISION.apply(12, 3), is(4));
    }

}