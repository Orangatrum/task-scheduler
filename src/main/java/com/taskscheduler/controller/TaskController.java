package com.taskscheduler.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskscheduler.engine.TaskScheduler;
import com.taskscheduler.model.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskScheduler scheduler;

    public TaskController(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(scheduler.ListQueues());
    }

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        scheduler.addTask(task);
        return ResponseEntity.ok("Task added successfully");
    }

    @GetMapping("/highest")
    public ResponseEntity<Task> viewHighestPriorityTask() {
        Task task = scheduler.view();
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/highest")
    public ResponseEntity<Task> pullHighestPriorityTask() {
        Task task = scheduler.pull();
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }
}