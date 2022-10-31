package DailyPlanner;

import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private int id;
    private String nameTask;
    private String descriptionTask;
    private TypeTask typeTask;
    private RepeatabilityOfTask repeatabilityOfTask;
    private LocalDate dataTask;

    private boolean isRemoteTask;

    public Task(String nameTask, String descriptionTask, int typeTask, int repeatabilityOfTask, LocalDate dataTask) {
        this.id = TaskService.getDailyPlanner().size() + 1;
        try {
            if (nameTask != null && !nameTask.isEmpty()) {
                this.nameTask = nameTask;
            } else {
                throw new InvalidParametrException();
            }
        } catch (InvalidParametrException e) {
            System.out.println("Введите корректный заголовок задачи");
            this.repeatabilityOfTask = RepeatabilityOfTask.SINGLE;
        }

        this.descriptionTask = descriptionTask;

        try {
            switch (typeTask) {
                case 1:
                    this.typeTask = TypeTask.WORKING;
                    break;
                case 2:
                    this.typeTask = TypeTask.PERSONAL;
                    break;
                default:
                    throw new InvalidParametrException();
            }
        } catch (InvalidParametrException e) {
            System.out.println("Некорректно введен тип задачи - по умолчанию проставлен личный тип");
            this.typeTask = TypeTask.PERSONAL;
        }

        try {
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
                    throw new InvalidParametrException();
            }
        } catch (InvalidParametrException e) {
            System.out.println("Повторяемость задачи указана некорректно - по умолчанию проставлена разовая повторяемость");
            this.repeatabilityOfTask = RepeatabilityOfTask.SINGLE;
        }

        this.dataTask = dataTask;

        this.isRemoteTask = false;

        System.out.println(TaskService.getDailyPlanner().size());
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
