package org.example;

import org.example.manager.TaskManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        System.out.println("Welcome");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Task | 2. List Tasks | 3. Mark Completed | 4. Delete Task | 5. Save | 6. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(title, description);
                    break;
                case "2":
                    taskManager.listTasks();
                    break;
                case "3":
                    System.out.print("Enter task ID to mark as completed: ");
                    handleIdInput(scanner, taskManager, "complete");
                    break;
                case "4":
                    System.out.print("Enter task ID to delete: ");
                    handleIdInput(scanner, taskManager, "delete");
                    break;
                case "5":
                    taskManager.saveToFile();
                    break;
                case "6":
                    System.out.println("Saving tasks and exiting. Goodbye!");
                    taskManager.saveToFile();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void handleIdInput(Scanner scanner, TaskManager taskManager, String action) {
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (action.equals("complete")) {
                taskManager.markAsCompleted(id);
            } else if (action.equals("delete")) {
                taskManager.deleteTask(id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid number.");
        }
    }
}