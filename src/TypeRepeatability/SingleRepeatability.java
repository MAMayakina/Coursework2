package TypeRepeatability;

import DailyPlanner.Repeatability;

import java.time.LocalDate;

public class SingleRepeatability implements Repeatability {

    public LocalDate nextRepeat(LocalDate dataRepeat) {
        return null;
    }

    public String getRepeatabilityOfTask() {
        return "разовая";
    }
}
