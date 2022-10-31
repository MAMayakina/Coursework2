import DailyPlanner.RepeatabilityOfTask;
import DailyPlanner.Task;
import DailyPlanner.TaskService;
import DailyPlanner.TypeTask;

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

        System.out.print("Выберите тип задачи: 1." + TypeTask.WORKING + ", 2." + TypeTask.PERSONAL + " ");
        int typeTask = scanner.nextInt();

        System.out.print("Выберите повторяемость задачи: 1." + RepeatabilityOfTask.SINGLE + ", 2." + RepeatabilityOfTask.DAILY + ", 3." + RepeatabilityOfTask.WEEKLY + ", 4." + RepeatabilityOfTask.MONTHLY + ", 5." + RepeatabilityOfTask.YEARLY + " ");
        int repeatabilityOfTask = scanner.nextInt();

        System.out.print("Введите дату задачи в формате \"yyyy-mm-dd\": ");
        LocalDate dataTask;
        try {
            dataTask = LocalDate.parse(scanner.next());
        } catch (RuntimeException e) {
            System.out.println("Дата введена некорректно. Полю дата присвоен сегодняшний день");
            dataTask = LocalDate.now();
        }

        Task newTask = new Task(nameTask, descriptionTask, typeTask, repeatabilityOfTask, dataTask);
        TaskService.addNewTask(newTask);
    }

    private static void deleteTask(Scanner scanner) {
        System.out.println("Введите id задачи которую хотите удалить");
        printDailyPlanner();
        Integer removeId = Integer.valueOf(scanner.next());
        removeTask(removeId);

    }

    private static void getTasksOnDate(Scanner scanner)  {
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