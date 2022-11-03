import DailyPlanner.Task;
import DailyPlanner.TaskService;
import DailyPlanner.TypeTask;
import MyException.InvalidParametrIdException;
import MyException.InvalidParametrNameException;
import MyException.InvalidParametrRepeatabilityException;
import MyException.InvalidParametrTypeException;

import java.time.LocalDate;
import java.util.Scanner;

import static DailyPlanner.ArchiveTasks.printArchive;
import static DailyPlanner.TaskService.*;
import static DailyPlanner.TypeTask.PERSONAL;


public class Main {
    public static void main(String[] args) throws InvalidParametrIdException {
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
                            getArchive();
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
        String nameTask;
        try {
            nameTask = checkNameTask(scanner.nextLine());
        } catch (InvalidParametrNameException e) {
            System.out.println("Введено некорректное имя задачи! По умолчанию проставлено имя \"Задача\"");
            nameTask = "Задача";
        }

        System.out.print("Введите описание задачи: ");
        String descriptionTask = scanner.nextLine();

        System.out.print("Введите тип задачи: " + TypeTask.WORKING.getType() + ", " + PERSONAL.getType() + " ");
        TypeTask typeTask;
        String typeTaskString = scanner.nextLine();
        try {
            typeTask = checkTypeTask(typeTaskString);
        } catch (InvalidParametrTypeException e) {
            System.out.println("Введен некорректный тип задачи! По умолчанию проставлен тип личная");
            typeTask = PERSONAL;
        }

        int repeatabilityOfTask;
        System.out.print("Выберите повторяемость задачи по номеру: 1.разовая, 2.ежедневная, 3.еженедельная, 4.ежемесячная, 5.ежегодная ");
        try {
            repeatabilityOfTask = checkRepeatabilityTask(Integer.parseInt(scanner.nextLine()));
        } catch (InvalidParametrRepeatabilityException e) {
            System.out.println("Введено некорректное значение повторяемости! По умолчанию проставлена повторяемость разовая");
            repeatabilityOfTask = 1;
        } catch (RuntimeException e) {
            System.out.println("Введено некорректное значение повторяемости! По умолчанию проставлена повторяемость разовая");
            repeatabilityOfTask = 1;
        }

        System.out.print("Введите дату задачи в формате \"yyyy-mm-dd\": ");
        LocalDate dataTask = LocalDate.parse(scanner.next());

        Task newTask = new Task(nameTask, descriptionTask, typeTask, repeatabilityOfTask, dataTask);
        TaskService.addNewTask(newTask);

    }

    private static String checkNameTask(String nameTask) throws InvalidParametrNameException {
        if (nameTask != null && !nameTask.isEmpty()) {
            return nameTask;
        } else {
            throw new InvalidParametrNameException();
        }
    }

    private static TypeTask checkTypeTask(String typeTaskString) throws InvalidParametrTypeException {
        for (TypeTask type : TypeTask.values()) {
            if (type.getType().equals(typeTaskString)) {
                return type;
            }
        }
        throw new InvalidParametrTypeException();
    }

    private static int checkRepeatabilityTask(int repeatability) throws InvalidParametrRepeatabilityException {
        if (repeatability > 0 && repeatability <= 5) {
            return repeatability;
        } else {
            throw new InvalidParametrRepeatabilityException();
        }
    }

    private static int checkRemoveId(int removeId) throws InvalidParametrIdException {
        if (getDailyPlanner().containsKey(removeId)) {
            return removeId;
        } else {
            throw new InvalidParametrIdException();
        }
    }

    private static void deleteTask(Scanner scanner) throws InvalidParametrIdException, RuntimeException {
        System.out.println("Введите id задачи которую хотите удалить");
        printDailyPlanner();
        try {
            int removeId = checkRemoveId(Integer.valueOf(scanner.next()));
            removeTask(removeId);
        } catch (InvalidParametrIdException e) {
            System.out.println("Задачи с таким Id не существует!");
        } catch (RuntimeException e) {
            System.out.println("Задачи с таким Id не существует!");
        }
    }

    private static void getTasksOnDate(Scanner scanner) {
        System.out.print("Введите дату в формате \"yyyy-mm-dd\", на которую необходимо вывести задачи: ");
        LocalDate dateTask = LocalDate.parse(scanner.next());
        getDailyPlannerOnDate(dateTask);
    }

    private static void groupingTasksByDate()  {
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