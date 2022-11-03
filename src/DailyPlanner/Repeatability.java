package DailyPlanner;

import java.time.LocalDate;

public interface Repeatability{

    abstract LocalDate nextRepeat(LocalDate dataRepeat);

    abstract String getRepeatabilityOfTask();
}
