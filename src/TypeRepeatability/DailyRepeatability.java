package TypeRepeatability;

import DailyPlanner.Repeatability;

import java.time.LocalDate;

public class DailyRepeatability implements Repeatability {

    public LocalDate nextRepeat(LocalDate dataRepeat) {
        dataRepeat = dataRepeat.plusDays(1);
        return dataRepeat;
    }

    public String getRepeatabilityOfTask() {
        return "ежедневная";
    }
}
