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

        // TODO fluent API?
        // overriding the default
        mmt.setMinDigits2(2);
        mmt.setMaxDigits1(3);
        mmt.setMinDigits2(2);
        mmt.setMaxDigits2(3);
        mmt.setAdditionEligible(true);
        mmt.setSubtraktionEligible(true);
        mmt.setMultiplikationEligible(true);
        mmt.setDivisionEligible(true);

        Consumer<Task> printWithResult = (task) -> {
            Integer result = task.getOperator()
                .apply(task.getFirstArgument(), task.getSecondArgument());
            System.out.println(String.format("%s = %d", task, result));
        };

        Stream.generate(mmt::generateTask)
            .limit(250)
            .forEach(printWithResult);

    }

}


