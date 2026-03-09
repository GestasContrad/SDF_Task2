package org.example.storage;

import org.example.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private static final String FILE_NAME = "tasks.csv";
    private static final String DELIMITER = ";";

    public static void saveTasks(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.println(task.getId() + DELIMITER +
                        task.getTitle() + DELIMITER +
                        task.getDescription() + DELIMITER +
                        task.isCompleted());
            }
            System.out.println("Tasks successfully saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static Task parseTask(String line) {
        String[] parts = line.split(DELIMITER);

        int id = Integer.parseInt(parts[0]);
        String title = parts[1];
        String description = parts[2];
        boolean completed = Boolean.parseBoolean(parts[3]);

        return new Task(id, title, description, completed);
    }

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    tasks.add(parseTask(line));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to load tasks from file: " + FILE_NAME);
        }
        return tasks;
    }
}