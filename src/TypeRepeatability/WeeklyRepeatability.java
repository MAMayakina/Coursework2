package TypeRepeatability;

import DailyPlanner.Repeatability;
import DailyPlanner.RepeatabilityOfTask;
import DailyPlanner.Task;

import java.time.LocalDate;

public class WeeklyRepeatability implements Repeatability {

    static LocalDate nextRepeat(LocalDate dataRepeat) {
        dataRepeat = dataRepeat.plusWeeks(1);
        return dataRepeat;
    }

}
