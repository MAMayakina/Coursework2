package TypeRepeatability;

import DailyPlanner.Repeatability;

import java.time.LocalDate;

public class MonthlyRepeatability implements Repeatability {

    public LocalDate nextRepeat(LocalDate dataRepeat) {
        dataRepeat = dataRepeat.plusMonths(1);
        return dataRepeat;
    }

    public String getRepeatabilityOfTask() {
        return "ежемесячная";
    }

}
