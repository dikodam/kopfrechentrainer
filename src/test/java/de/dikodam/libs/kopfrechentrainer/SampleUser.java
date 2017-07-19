package de.dikodam.libs.kopfrechentrainer;

import org.junit.Ignore;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class SampleUser {

    @Ignore
    @Test
    public void main() {
        MentalMathTrainer mmt = new MentalMathTrainer();

        // overriding the default
        mmt.setMinDigits2(2)
            .setMaxDigits1(3)
            .setMinDigits2(2)
            .setMaxDigits2(3)
            .setAdditionEnabled(true)
            .setSubtractionEnabled(true)
            .setMultiplikationEnabled(true);
            //.setDivisionEnabled(false);

        Consumer<Task> printWithResult = (task) -> System.out.println(String.format("%s = %d", task, task.getResult()));

        mmt.generateTasks(250)
            .forEach(printWithResult);
    }

}


