package com.taskscheduler.engine;
//used for iterator
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import com.taskscheduler.model.Task;
import org.springframework.stereotype.Service;
@Service
public class TaskScheduler {
	PriorityQueue<Task> taskQueue = new PriorityQueue<>(1);
	
	public void addTask(Task task) {
		
		taskQueue.add(task); // add new task to queue. task.java's compareTo method will handle the comparisions 
	}
	public Task view() { //method used to view the highest priority task without removing it
		if (taskQueue.isEmpty()){
			return null;
		}
		Task highest = taskQueue.peek();
		return highest;
	}
	public Task pull() { //removes highest priority task to be processed
		if (taskQueue.isEmpty()){
			return null;
		}
		Task removedTask = taskQueue.remove();
		return removedTask;
	}
	public List<Task> ListQueues() {
		List<Task> listOfTasks = new ArrayList<>(taskQueue);
		return listOfTasks;
	}
	
	
}
