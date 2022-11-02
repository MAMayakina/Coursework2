import DailyPlanner.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

import static DailyPlanner.ArchiveTasks.printArchive;
import static DailyPlanner.TaskService.*;


public class Main {
    public static void main(String[] args) throws ParseException {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                            getTasksOnDate(scanner);
                            break;
                        case 4:
                            groupingTasksByDate();
                            break;
                        case 5:
                            getAllTasks();
                            break;
                        case 6:
                            getAllTasks();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        scanner.nextLine();
        String nameTask = scanner.nextLine();

        System.out.print("Введите описание задачи: ");
        String descriptionTask = scanner.nextLine();

        System.out.print("Введите тип задачи: " + TypeTask.WORKING + ", " + TypeTask.PERSONAL + " ");
        TypeTask typeTask;
        try {
            typeTask = TypeTask.valueOf(scanner.nextLine());
        } catch (RuntimeException e) {
            System.out.println("Тип задачи указан некорректно. По умолчанию проставлен PERSONAL");
            typeTask = TypeTask.PERSONAL;
        }

        System.out.print("Введите повторяемость задачи: " + RepeatabilityOfTask.SINGLE + ", " + RepeatabilityOfTask.DAILY + ", " + RepeatabilityOfTask.WEEKLY + ", " + RepeatabilityOfTask.MONTHLY + ", " + RepeatabilityOfTask.YEARLY + " ");
        RepeatabilityOfTask repeatabilityOfTask;
        try {
            repeatabilityOfTask = RepeatabilityOfTask.valueOf(scanner.nextLine());
        } catch (RuntimeException e) {
            System.out.println("Повторяемость задачи указана некорректно. По умолчанию проставлено SINGLE");
            repeatabilityOfTask = RepeatabilityOfTask.SINGLE;
        }

        System.out.print("Введите дату задачи в формате \"yyyy-mm-dd\": ");
        LocalDate dataTask;
        try {
            dataTask = LocalDate.parse(scanner.next());
        } catch (RuntimeException e) {
            System.out.println("Дата введена некорректно. Полю дата присвоен сегодняшний день");
            dataTask = LocalDate.now();
        }

        try {
            Task newTask = new Task(nameTask, descriptionTask, typeTask, repeatabilityOfTask, dataTask);
            TaskService.addNewTask(newTask);
        } catch (InvalidParametrException e) {
            System.out.println("Введите корректный заголовок задачи");
        }

    }

    private static void deleteTask(Scanner scanner) {
        System.out.println("Введите id задачи которую хотите удалить");
        printDailyPlanner();
        Integer removeId = Integer.valueOf(scanner.next());

        try {
        removeTask(removeId);
        } catch (InvalidParametrException e) {
            System.out.println("Задачи с таким id не существует");
        }
    }

    private static void getTasksOnDate(Scanner scanner) {
        System.out.print("Введите дату в формате \"yyyy-mm-dd\", на которую необходимо вывести задачи: ");
        LocalDate dateTask = LocalDate.parse(scanner.next());
        getDailyPlannerOnDate(dateTask);
    }

    private static void groupingTasksByDate() throws ParseException {
        groupingTasks();
    }

    private static void getAllTasks() {
        printDailyPlanner();
    }

    private static void getArchive() {
        printArchive();
    }

    private static void printMenu() {
        System.out.println("Выберите пункт меню: ");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("4. Сгруппировать задачи по датам");
        System.out.println("5. Получить все задачи");
        System.out.println("6. Получить удаленные задачи");
        System.out.println("0. Выход");
    }
}