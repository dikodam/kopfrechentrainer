package de.dikodam.libs.kopfrechentrainer;

import mockit.Deencapsulation;
import mockit.Tested;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

import static de.dikodam.libs.kopfrechentrainer.Operation.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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

    //  @Test
    //public void produceAufgabe() throws Exception {
    //  // Fehlkonfiguration
    //  expectedException.expect(IllegalStateException.class);
//    }

    @Test
    public void produceAufgabe() {
        Assert.fail("not implemented");
    }

}