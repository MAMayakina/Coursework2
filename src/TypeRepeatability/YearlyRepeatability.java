package TypeRepeatability;

import DailyPlanner.Repeatability;

import java.time.LocalDate;

public class YearlyRepeatability implements Repeatability {

    public LocalDate nextRepeat(LocalDate dataRepeat) {
        dataRepeat = dataRepeat.plusYears(1);
        return dataRepeat;
    }

    public String getRepeatabilityOfTask() {
        return "ежегодная";
    }

}
