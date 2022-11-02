package TypeRepeatability;

import DailyPlanner.Repeatability;
import DailyPlanner.RepeatabilityOfTask;

import java.time.LocalDate;

public class MonthlyRepeatability implements Repeatability {

    static LocalDate nextRepeat(LocalDate dataRepeat) {
        dataRepeat = dataRepeat.plusMonths(1);
        return dataRepeat;
    }

}
