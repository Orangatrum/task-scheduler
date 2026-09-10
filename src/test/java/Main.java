import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.*;
import engine.*;
import model.Priority;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
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
		System.out.println("Please enter date in the format (YYYY-MM-DD): ");
		dueDate= scanner.nextLine().toUpperCase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
		LocalDate date = LocalDate.parse(dueDate, formatter);
		int id = taskQueue.size();
		String strId = Integer.toString(id);

	}

}
