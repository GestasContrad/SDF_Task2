package org.example.model;

// It is task object which use in task manager

public class Task {
    private int id;
    private String title;
    private String description;
    private boolean completed;

    public Task(int id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }

    private String getStatusText() {
        return completed ? "Completed" : "Pending";
    }

    @Override
    public String toString() {
        return id + ". " + title + " (" + description + ") - " + getStatusText();
    }
}
