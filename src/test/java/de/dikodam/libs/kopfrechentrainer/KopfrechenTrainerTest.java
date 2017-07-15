package de.dikodam.libs.kopfrechentrainer;

import mockit.Deencapsulation;
import mockit.Tested;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class KopfrechenTrainerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Tested
    private KopfrechenTrainer tested;

    @Test
    public void defaultConstructor() {
        assertThat(tested.getMinStellenanzahl1(), is(1));
        assertThat(tested.getMinStellenanzahl2(), is(1));
        assertThat(tested.getMaxStellenanzahl1(), is(2));
        assertThat(tested.getMaxStellenanzahl2(), is(2));
        assertThat(tested.isAdditionRechnen(), is(true));
        assertThat(tested.isSubtraktionRechnen(), is(false));
        assertThat(tested.isMultiplikationRechnen(), is(false));
        assertThat(tested.isDivisionRechnen(), is(false));
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
    public void setPlusRechnenTrue() throws Exception {
        String feldName = "additionRechnen";
        Deencapsulation.setField(tested, feldName, false);

        tested.setAdditionRechnen(true);
        assertThat(Deencapsulation.getField(tested, feldName), is(true));

        tested.setAdditionRechnen(false);
        assertThat(Deencapsulation.getField(tested, feldName), is(false));
    }


    @Test
    public void setMinusRechnen() throws Exception {
        String feldName = "subtraktionRechnen";
        Deencapsulation.setField(tested, feldName, false);

        tested.setSubtraktionRechnen(true);
        assertThat(Deencapsulation.getField(tested, feldName), is(true));

        tested.setSubtraktionRechnen(false);
        assertThat(Deencapsulation.getField(tested, feldName), is(false));
    }

    @Test
    public void setMalRechnen() throws Exception {
        String feldName = "multiplikationRechnen";
        Deencapsulation.setField(tested, feldName, false);

        tested.setMultiplikationRechnen(true);
        assertThat(Deencapsulation.getField(tested, feldName), is(true));

        tested.setMultiplikationRechnen(false);
        assertThat(Deencapsulation.getField(tested, feldName), is(false));
    }

    @Test
    public void setGeteiltErlaubt() throws Exception {
        String feldName = "divisionRechnen";
        Deencapsulation.setField(tested, feldName, false);

        tested.setDivisionRechnen(true);
        assertThat(Deencapsulation.getField(tested, feldName), is(true));

        tested.setDivisionRechnen(false);
        assertThat(Deencapsulation.getField(tested, feldName), is(false));
    }

    @Test
    public void operationen() {
        BinaryOperator<Integer> addition = Deencapsulation.getField(KopfrechenTrainer.class, "ADDITION");
        BinaryOperator<Integer> subtraktion = Deencapsulation.getField(KopfrechenTrainer.class, "SUBTRAKTION");
        BinaryOperator<Integer> multiplikation = Deencapsulation.getField(KopfrechenTrainer.class, "MULTIPLIKATION");
        BinaryOperator<Integer> division = Deencapsulation.getField(KopfrechenTrainer.class, "DIVISION");

        assertThat(addition.apply(1, 2), is(3));
        assertThat(subtraktion.apply(1, 2), is(-1));
        assertThat(multiplikation.apply(3, 4), is(12));
        assertThat(division.apply(12, 3), is(4));
    }

//    @Test
    //  public void go() throws Exception{
    //  expectedException.expect(IllegalStateException.class);
    //  // Fehlkonfiguration
    //}

}