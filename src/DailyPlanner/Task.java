package DailyPlanner;

import java.time.LocalDate;
import java.util.Objects;

public class Task {

    public enum TypeTask {
        WORKING("рабочая"),
        PERSONAL("личная");

        private String type;

        TypeTask(String type) {
            this.type = type;
        }
    }

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
    }

    private String nameTask;
    private String descriptionTask;
    private TypeTask typeTask;
    private RepeatabilityOfTask repeatabilityOfTask;
    private LocalDate dataTask;

    private boolean isRemoteTask;

    public Task(String nameTask, String descriptionTask, int typeTask, int repeatabilityOfTask, LocalDate dataTask) {
        if (nameTask != null && !nameTask.isEmpty()) {
            this.nameTask = nameTask;
        } else {
            throw new RuntimeException("Введите корректный заголовок задачи!");
        }

        this.descriptionTask = descriptionTask;

        switch (typeTask) {
            case 1:
                this.typeTask = TypeTask.WORKING;
                break;
            case 2:
                this.typeTask = TypeTask.PERSONAL;
                break;
            default:
                System.out.println("Некорректно введен тип задачи - по умолчанию проставлен личный тип");
                this.typeTask = TypeTask.PERSONAL;
                break;
        }

        switch (repeatabilityOfTask) {
            case 1:
                this.repeatabilityOfTask = RepeatabilityOfTask.SINGLE;
                break;
            case 2:
                this.repeatabilityOfTask = RepeatabilityOfTask.DAILY;
                break;
            case 3:
                this.repeatabilityOfTask = RepeatabilityOfTask.WEEKLY;
                break;
            case 4:
                this.repeatabilityOfTask = RepeatabilityOfTask.MONTHLY;
                break;
            case 5:
                this.repeatabilityOfTask = RepeatabilityOfTask.YEARLY;
                break;
            default:
                System.out.println("Повторяемость задачи указана некорректно - по умолчанию проставлена разовая повторяемость");
                this.repeatabilityOfTask = RepeatabilityOfTask.SINGLE;
                break;
        }

        try {
            this.dataTask = dataTask;
        } catch (RuntimeException e) {
            System.out.println("Дата введена некорректно! Полю дата присвоена текущая дата");
        }

        this.isRemoteTask = false;

        TaskService.addNewTask(this);
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
        return isRemoteTask == task.isRemoteTask && Objects.equals(nameTask, task.nameTask) && Objects.equals(descriptionTask, task.descriptionTask) && typeTask == task.typeTask && repeatabilityOfTask == task.repeatabilityOfTask && Objects.equals(dataTask, task.dataTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTask, descriptionTask, typeTask, repeatabilityOfTask, dataTask, isRemoteTask);
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

    public void setTypeTask(int typeTask) {
        switch (typeTask) {
            case 1:
                this.typeTask = TypeTask.WORKING;
                break;
            case 2:
                this.typeTask = TypeTask.PERSONAL;
                break;
            default:
                System.out.println("Некорректно введен тип задачи - по умолчанию проставлен личный тип");
                this.typeTask = TypeTask.PERSONAL;
                break;
        }
    }

    public RepeatabilityOfTask getRepeatabilityOfTask() {
        return repeatabilityOfTask;
    }

    public void setRepeatabilityOfTask(int repeatabilityOfTask) {
        switch (repeatabilityOfTask) {
            case 1:
                this.repeatabilityOfTask = RepeatabilityOfTask.SINGLE;
                break;
            case 2:
                this.repeatabilityOfTask = RepeatabilityOfTask.DAILY;
                break;
            case 3:
                this.repeatabilityOfTask = RepeatabilityOfTask.WEEKLY;
                break;
            case 4:
                this.repeatabilityOfTask = RepeatabilityOfTask.MONTHLY;
                break;
            case 5:
                this.repeatabilityOfTask = RepeatabilityOfTask.YEARLY;
                break;
            default:
                System.out.println("Повторяемость задачи указана некорректно - по умолчанию проставлена разовая повторяемость");
                this.repeatabilityOfTask = RepeatabilityOfTask.SINGLE;
                break;
        }
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
