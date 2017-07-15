package de.dikodam.libs.kopfrechentrainer;

import org.junit.Ignore;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class SampleUser {

    @Ignore
    @Test
    public void main() {
        KopfrechenTrainer krt = new KopfrechenTrainer();

        // TODO fluent API?
        // overriding the default
        krt.setMinStellenanzahl1(2);
        krt.setMaxStellenanzahl1(3);
        krt.setMinStellenanzahl2(2);
        krt.setMaxStellenanzahl2(3);
        krt.setAdditionErlaubt(true);
        krt.setSubtraktionErlaubt(true);
        krt.setMultiplikationErlaubt(true);
        krt.setDivisionErlaubt(true);

        Consumer<Aufgabe> ausgabeMitErgebnis = (aufgabe) -> {
            Integer result = aufgabe.getOperator()
                .apply(aufgabe.getErstesArgument(), aufgabe.getZweitesArgument());
            System.out.println(String.format("%s = %d", aufgabe, result));
        };

        Stream.generate(krt::produceAufgabe)
            .limit(250)
            .forEach(ausgabeMitErgebnis);

    }

}


