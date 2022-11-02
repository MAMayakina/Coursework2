package DailyPlanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TaskService implements Repeatability {

    private static Map<Integer, Task> dailyPlanner = new HashMap<>();

    public static void addNewTask(Task newTask) {
        dailyPlanner.put(newTask.getId(), newTask);
        System.out.println("Задача добавлена в мапу");
    }


    public static void removeTask(Integer removeId) throws InvalidParametrException {
        if (dailyPlanner.containsKey(removeId)) {
            dailyPlanner.get(removeId).setRemoteTask(true);
            ArchiveTasks.getArchive().add(dailyPlanner.get(removeId));
        } else {
            throw new InvalidParametrException();
        }

    }


    public static void printDailyPlanner() {
        System.out.println("Ежедневник");
        for (int i = 1; i <= dailyPlanner.size(); i++) {
            System.out.println("Задача id " + i + ": " + dailyPlanner.get(i));
        }
    }

    public static List getDailyPlannerOnDate(LocalDate data) {
        System.out.println("Задачи на день " + data + ":");
        List<Task> todayTasks = new LinkedList<>();
        //заполняем список
        for (int i = 1; i <= dailyPlanner.size(); i++) {
            if (checkTask(dailyPlanner.get(i), data)) {
                todayTasks.add(dailyPlanner.get(i));
            }
        }
        //выводим список
        if (todayTasks.size() == 0) {
            System.out.println("Задач в указанную дату нет");
        } else {
            for (int i = 0; i < todayTasks.size(); i++) {
                System.out.println((i + 1) + ". Задача (id-" + i + ") " + todayTasks.get(i));
            }
        }
        return todayTasks;
    }

    public static boolean checkTask(Task task, LocalDate setData) {
        LocalDate dataRepeatTask = task.getDataTask();
        while (true) {
            if (dataRepeatTask.isEqual(setData)) {
                return true;
            } else if (task.getRepeatability().equals(RepeatabilityOfTask.SINGLE)) {
                return false;
            } else if (dataRepeatTask.isAfter(setData)) {
                return false;
            } else {
                dataRepeatTask = task.getRepeatability().nextRepeat(dataRepeatTask);
            }
        }
    }


    public static void groupingTasks() {
        Map<LocalDate, List<Task>> groupingTasks = new HashMap<>();
        for (int i = 1; i <= dailyPlanner.size(); i++) {
            if (!groupingTasks.containsKey(dailyPlanner.get(i).getDataTask())) {
                groupingTasks.put(dailyPlanner.get(i).getDataTask(), getDailyPlannerOnDate(dailyPlanner.get(i).getDataTask()));
            }
        }
        System.out.println("Задачи, сгруппированные по датам");
        for (Map.Entry<LocalDate, List<Task>> entry : groupingTasks.entrySet()) {
            System.out.println(entry.getKey());
            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.println(entry.getValue().get(i));
            }
        }
    }

    public static Map<Integer, Task> getDailyPlanner() {
        return dailyPlanner;
    }
}
