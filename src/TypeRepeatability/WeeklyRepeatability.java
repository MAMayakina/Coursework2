package TypeRepeatability;

import DailyPlanner.Repeatability;

import java.time.LocalDate;

public class WeeklyRepeatability implements Repeatability {

    public LocalDate nextRepeat(LocalDate dataRepeat) {
        dataRepeat = dataRepeat.plusWeeks(1);
        return dataRepeat;
    }

    public String getRepeatabilityOfTask() {
        return "еженедельная";
    }
}
