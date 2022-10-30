package DailyPlanner;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class TaskService {

    public static Map<Integer, Task> dailyPlanner = new HashMap<>();

    public static void addNewTask(Task newTask) {
        Integer newId = getId();
        dailyPlanner.put(newId, newTask);
    }

    public static Integer getId() {
        Integer id = dailyPlanner.size() + 1;
        return id;
    }

    public static void removeTask(Integer removeId) {
        dailyPlanner.get(removeId).setRemoteTask(true);
        ArchiveTasks.archive.add(dailyPlanner.get(removeId));
    }


    public static void printDailyPlanner() {
        System.out.println("Ежедневник");
        for (int i = 1; i <= dailyPlanner.size(); i++) {
            System.out.println("Задача id " + i + ": " + dailyPlanner.get(i));
        }
    }

    public static void printDailyPlannerOnDate(LocalDate data) throws ParseException {
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
                System.out.println((i+1) + ". Задача (id-" + i + ") " + todayTasks.get(i));
            }
        }
    }

    public static boolean checkTask(Task task, LocalDate setData) {
        LocalDate dataRepeatTask = task.getDataTask();
        while (true) {
            if (dataRepeatTask.isEqual(setData)) {
                return true;
            } else if (task.getRepeatabilityOfTask().equals(Task.RepeatabilityOfTask.SINGLE)) {
                return false;
            } else if (dataRepeatTask.isAfter(setData)) {
                return false;
            } else {
                dataRepeatTask = nextRepeat(task, dataRepeatTask);
            }
        }
    }

    public static LocalDate nextRepeat(Task task, LocalDate dataRepeat) {
        switch (task.getRepeatabilityOfTask()) {
            case DAILY:
                dataRepeat = dataRepeat.plusDays(1);
                break;
            case WEEKLY:
                dataRepeat = dataRepeat.plusWeeks(1);
                break;
            case MONTHLY:
                dataRepeat = dataRepeat.plusMonths(1);
                break;
            case YEARLY:
                dataRepeat = dataRepeat.plusYears(1);
                break;
        }
        return dataRepeat;
    }

    public static void groupingTasks() throws ParseException {
        System.out.println("Задачи, сгруппированные по датам");
        List<LocalDate> listDates = getAllDates();
        for (LocalDate date : listDates) {
            printDailyPlannerOnDate(date);
        }
    }

    public static List getAllDates() {
        List<LocalDate> allDates = new LinkedList<>();
        for (int i = 1; i <= dailyPlanner.size(); i++) {
            allDates.add(dailyPlanner.get(i).getDataTask());
        }
        return allDates;

    }
}
