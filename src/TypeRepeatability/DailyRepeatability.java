package TypeRepeatability;

import DailyPlanner.Repeatability;
import DailyPlanner.RepeatabilityOfTask;
import DailyPlanner.Task;

import java.time.LocalDate;

public class DailyRepeatability implements Repeatability {

    static LocalDate nextRepeat(LocalDate dataRepeat) {
        dataRepeat = dataRepeat.plusDays(1);
        return dataRepeat;
    }


}
