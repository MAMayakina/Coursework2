package DailyPlanner;

public enum RepeatabilityOfTask {
    SINGLE("разовая"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    YEARLY("ежегодная");

    private String repeatability;

    RepeatabilityOfTask(String repeatability) {
        this.repeatability = repeatability;
    }

    public String getRepeatability() {
        return repeatability;
    }

    public void setRepeatability(int repeatability) {
        try {
            switch (repeatability) {
                case 1:
                    this.repeatability = String.valueOf(SINGLE);
                    break;
                case 2:
                    this.repeatability = String.valueOf(DAILY);
                    break;
                case 3:
                    this.repeatability = String.valueOf(WEEKLY);
                    break;
                case 4:
                    this.repeatability = String.valueOf(MONTHLY);
                    break;
                case 5:
                    this.repeatability = String.valueOf(YEARLY);
                    break;
                default:
                    throw new InvalidParametrException();
            }
        } catch (InvalidParametrException e) {
            System.out.println("Повторяемость задачи указана некорректно - по умолчанию проставлена разовая повторяемость");
            this.repeatability = String.valueOf(SINGLE);
        }
    }
}
