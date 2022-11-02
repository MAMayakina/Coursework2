package DailyPlanner;

import TypeRepeatability.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static DailyPlanner.RepeatabilityOfTask.*;



public class Task {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private int id ;
    private String nameTask;
    private String descriptionTask;
    private TypeTask typeTask;
    private RepeatabilityOfTask repeatabilityOfTask;
    private Repeatability repeatability;
    private LocalDate dataTask;

    private boolean isRemoteTask;


    public Task(String nameTask, String descriptionTask, TypeTask typeTask, RepeatabilityOfTask repeatabilityOfTask, LocalDate dataTask) throws InvalidParametrException {
        this.id = COUNTER.getAndIncrement();

        if (nameTask != null && !nameTask.isEmpty()) {
            this.nameTask = nameTask;
        } else {
            throw new InvalidParametrException();
        }


        this.descriptionTask = descriptionTask;


        switch (typeTask) {
            case WORKING:
                this.typeTask = TypeTask.WORKING;
                break;
            case PERSONAL:
                this.typeTask = TypeTask.PERSONAL;
                break;
            default:
                System.out.println("Некорректно введен тип задачи - по умолчанию проставлен личный тип");
                this.typeTask = TypeTask.PERSONAL;
        }
        switch (repeatabilityOfTask) {
            case SINGLE:
                this.repeatabilityOfTask = SINGLE;
                this.repeatability = new SingleRepeatability();
                break;
            case DAILY:
                this.repeatabilityOfTask = DAILY;
                this.repeatability = new DailyRepeatability();
                break;
            case WEEKLY:
                this.repeatabilityOfTask = WEEKLY;
                this.repeatability = new WeeklyRepeatability();
                break;
            case MONTHLY:
                this.repeatabilityOfTask = MONTHLY;
                this.repeatability = new MonthlyRepeatability();
                break;
            case YEARLY:
                this.repeatabilityOfTask = YEARLY;
                this.repeatability = new YearlyRepeatability();
                break;
            default:
                System.out.println("Повторяемость задачи указана некорректно - по умолчанию проставлена разовая повторяемость");
                this.repeatabilityOfTask = SINGLE;
                this.repeatability = new SingleRepeatability();
        }

        this.dataTask = dataTask;

        this.isRemoteTask = false;

    }

    @Override
    public String toString() {
        if (isRemoteTask) {
            return nameTask +
                    " (" + descriptionTask +
                    "), тип задачи " + typeTask +
                    ", повторяемость " + repeatabilityOfTask +
                    " (дата начала " + dataTask + ")" +
                    " - задача помечена на удаление";
        } else {
            return nameTask +
                    " (" + descriptionTask +
                    "), тип задачи " + typeTask +
                    ", повторяемость " + repeatabilityOfTask +
                    " (дата начала " + dataTask + ")";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && isRemoteTask == task.isRemoteTask && Objects.equals(nameTask, task.nameTask) && Objects.equals(descriptionTask, task.descriptionTask) && typeTask == task.typeTask && Objects.equals(repeatability, task.repeatability) && Objects.equals(dataTask, task.dataTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTask, descriptionTask, typeTask, repeatability, dataTask, isRemoteTask);
    }

    public int getId() {
        return id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        if (nameTask != null && !nameTask.isEmpty() && !nameTask.isBlank()) {
            this.nameTask = nameTask;
        }
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        if (descriptionTask != null && !descriptionTask.isEmpty() && !descriptionTask.isBlank()) {
            this.descriptionTask = descriptionTask;
        }
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public RepeatabilityOfTask getRepeatabilityOfTask() {
        return repeatabilityOfTask;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public LocalDate getDataTask() {
        return dataTask;
    }

    public void setDataTask(LocalDate dataTask) {
        this.dataTask = dataTask;
    }

    public boolean isRemoteTask() {
        return isRemoteTask;
    }

    protected void setRemoteTask(boolean remoteTask) {
        isRemoteTask = remoteTask;
    }
}
