package DailyPlanner;

import java.util.LinkedList;
import java.util.List;

public class ArchiveTasks {
    private static List<Task> archive = new LinkedList<>();

    public static void printArchive(){
        System.out.println("Архив задач");
        for(int i=0;i< archive.size();i++){
            System.out.println("Задача "+ archive.get(i));
        }
    }

    public static List<Task> getArchive() {
        return archive;
    }
}
