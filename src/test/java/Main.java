import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import model.*;
import engine.*;
import model.Priority;

public class Main {

	public static void main(String[] args) {
		TaskScheduler scheduler = new TaskScheduler();
		
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		
		while (option != 5){
		System.out.println("Select 1 option (1. Add, 2. View Top Task, 3. Complete Task, 4. List All, 5. Exit): ");
		try {
		    option = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
		    System.out.println("Please enter a valid number (1-5).");
		    continue; // Skip the rest of the loop iteration
		}
		switch(option) {
		case 1:
			Priority userPriority = null;
			String dueDate = " ";
			System.out.println("Enter your task: ");
			String task = scanner.nextLine();
			while (userPriority == null) {
	            System.out.print("Enter priority level (HIGH, MEDIUM, LOW): ");
	            String input = scanner.nextLine().trim().toUpperCase();

	            try {
	                // Convert valid string to enum
	                userPriority = Priority.valueOf(input); 
	            } catch (IllegalArgumentException e) {
	                System.out.println("Invalid input. Please enter HIGH, MEDIUM, or LOW.");
	            }
	        }
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = null;
			
			while (date == null) {
			    System.out.println("Please enter date in the format (YYYY-MM-DD): ");
			    String dueDate1 = scanner.nextLine().trim();
			    try {
			        date = LocalDate.parse(dueDate1, formatter);
			    } catch (DateTimeParseException e) {
			        System.out.println("Invalid date format! Please use YYYY-MM-DD.");
			    }
			}
			String nextID = java.util.UUID.randomUUID().toString();
			scheduler.addTask(new Task(nextID, task, userPriority, date));
			break;
		case 2:
			Task top = scheduler.view();
			if (top != null) {
				System.out.println(top);
			}else {
				System.out.println("No tasks are here!");
			}
			break;
		case 3:
			Task completedTask = scheduler.pull();
			if (completedTask != null) {
				System.out.println(completedTask);
			}else {
				System.out.println("No tasks are here!");
			}
			break;
		case 4:
			List<Task> tasks = scheduler.ListQueues();
			Iterator<Task> it = tasks.iterator();
			if(tasks.isEmpty()) {
				System.out.println("No tasks");
			}else {
			while(it.hasNext()) {
				Task current = it.next();
				System.out.println(current);
			}
			}
			break;
		case 5:
			System.out.println("Exiting!");
			scanner.close();
			break;
		default:
			System.out.println("Invalid option");
			break;
		}
		}//end of option while loop
	}//end of main


}//end of class
