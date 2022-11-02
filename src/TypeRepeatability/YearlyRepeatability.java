package TypeRepeatability;

import DailyPlanner.Repeatability;
import DailyPlanner.RepeatabilityOfTask;
import DailyPlanner.Task;

import java.time.LocalDate;

public class YearlyRepeatability implements Repeatability {

    static LocalDate nextRepeat(LocalDate dataRepeat) {
        dataRepeat = dataRepeat.plusYears(1);
        return dataRepeat;
    }

}
