package service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Audit <T> {

    private static String className;

    private static FileWriter fileWriter;

    public Audit(Class<T> typeClass)
    {
        className = typeClass.getSimpleName();
    }

    public static void logAction(String actionName, LocalDateTime timestamp)
    {
        try {
            fileWriter = new FileWriter("C:\\Users\\andre\\IdeaProjects\\ProiectPAO\\src\\service\\audit.csv", true);
            fileWriter
                    .append(actionName)
                    .append(String.valueOf(','))
                    .append(timestamp.toString())
                    .append(String.valueOf('\n'));

            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

}
