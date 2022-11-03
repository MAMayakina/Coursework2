package DailyPlanner;

import TypeRepeatability.*;

import java.time.LocalDate;
import java.util.Objects;


public class Task {
    private static int counter = 1;

    private int id;
    private String nameTask;
    private String descriptionTask;
    private TypeTask typeTask;
    private Repeatability repeatability;
    private LocalDate dataTask;

    private boolean isRemoteTask;


    public Task(String nameTask, String descriptionTask, TypeTask typeTask, int repeatabilityOfTask, LocalDate dataTask) {
        this.id = counter++;

        this.nameTask = nameTask;

        this.descriptionTask = descriptionTask;

        this.typeTask = typeTask;

        switch (repeatabilityOfTask) {
            case 1:
                this.repeatability = new SingleRepeatability();
                break;
            case 2:
                this.repeatability = new DailyRepeatability();
                break;
            case 3:
                this.repeatability = new WeeklyRepeatability();
                break;
            case 4:
                this.repeatability = new MonthlyRepeatability();
                break;
            case 5:
                this.repeatability = new YearlyRepeatability();
                break;
        }

        this.dataTask = dataTask;

        this.isRemoteTask = false;

    }

    @Override
    public String toString() {
        if (isRemoteTask) {
            return nameTask +
                    " (" + descriptionTask +
                    "), тип задачи " + typeTask.getType() +
                    ", повторяемость " + repeatability.getRepeatabilityOfTask() +
                    " (дата начала " + dataTask + ")" +
                    " - задача помечена на удаление";
        } else {
            return nameTask +
                    " (" + descriptionTask +
                    "), тип задачи " + typeTask.getType() +
                    ", повторяемость " + repeatability.getRepeatabilityOfTask() +
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
