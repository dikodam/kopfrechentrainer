package de.dikodam.libs.kopfrechentrainer;

import mockit.Tested;
import org.junit.Test;

import static de.dikodam.libs.kopfrechentrainer.Operation.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OperationTest {

    @Tested
    private Operation tested;

    @Test
    public void operationen() throws Exception {
        assertThat(ADDITION.apply(1, 2), is(3));
        assertThat(SUBTRAKTION.apply(1, 2), is(-1));
        assertThat(MULTIPLIKATION.apply(3, 4), is(12));
        assertThat(DIVISION.apply(12, 3), is(4));
    }

}