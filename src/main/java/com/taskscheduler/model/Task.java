package com.taskscheduler.model;
import java.time.LocalDate;
public class Task implements Comparable<Task> {
    private String id;
    private String title;
    private Priority priority;
    private LocalDate dueDate;
    private boolean isCompleted;

    public Task(String id, String title, Priority priority, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    // Compare tasks based on Priority level first, then Due Date
    public int compareTo(Task other) {
        int priorityComparison = Integer.compare(this.priority.getLevel(), other.priority.getLevel());
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        return this.dueDate.compareTo(other.dueDate);
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {
        return String.format("ID: [%s] %s | Priority: %s | Due: %s | Completed: %s",
                id, title, priority, dueDate, isCompleted);
    }
}
