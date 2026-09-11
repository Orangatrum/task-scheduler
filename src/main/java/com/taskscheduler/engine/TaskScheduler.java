package engine;
import java.util.*; //used for iterator

import java.util.PriorityQueue;
import model.*; //importing the model package
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
