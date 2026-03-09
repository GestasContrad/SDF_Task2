package org.example.manager;

import org.example.model.Task;
import org.example.storage.FileStorage;

import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int nextId;

    public TaskManager() {
        this.tasks = FileStorage.loadTasks();
        this.nextId = calculateNextId();
    }

    private int calculateNextId() {
        int maxId = 0;
        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        return maxId + 1;
    }

    public void addTask(String title, String description) {
        Task newTask = new Task(nextId++, title, description, false);
        tasks.add(newTask);
        System.out.println("Task added successfully! ID: " + newTask.getId());
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("\n--- Task List ---");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("-----------------");
    }

    public void markAsCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                System.out.println("Task " + id + " marked as completed.");
                return;
            }
        }
        System.out.println("Task with ID " + id + " not found.");
    }

    public void deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            System.out.println("Task " + id + " deleted successfully.");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public void saveToFile() {
        FileStorage.saveTasks(tasks);
    }
}