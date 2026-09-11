

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import model.Priority;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import engine.TaskScheduler;
class TaskSchedulerTest {
	private TaskScheduler scheduler;

    @BeforeEach
    void setUp() {
        scheduler = new TaskScheduler();
    }

    @Test
    @DisplayName("Should retrieve high priority task before low priority task")
    void testPriorityOrdering() {
        Task lowTask = new Task("1", "Low Priority Task", Priority.LOW, LocalDate.now().plusDays(1));
        Task highTask = new Task("2", "High Priority Task", Priority.HIGH, LocalDate.now().plusDays(5));

        scheduler.addTask(lowTask);
        scheduler.addTask(highTask);

        // High priority should be served first regardless of insertion order or date
        assertEquals(highTask, scheduler.view());
    }

    @Test
    @DisplayName("Should order tasks by due date when priorities are identical")
    void testSamePriorityDateOrdering() {
        LocalDate earlierDate = LocalDate.now().plusDays(1);
        LocalDate laterDate = LocalDate.now().plusDays(10);

        Task laterTask = new Task("1", "Later Task", Priority.MEDIUM, laterDate);
        Task earlierTask = new Task("2", "Earlier Task", Priority.MEDIUM, earlierDate);

        scheduler.addTask(laterTask);
        scheduler.addTask(earlierTask);

        // Earlier due date should come out first
        assertEquals(earlierTask, scheduler.view());
    }

    @Test
    @DisplayName("Should return null when viewing or pulling from an empty queue")
    void testEmptyQueueBehavior() {
        assertNull(scheduler.view());
        assertNull(scheduler.pull());
    }

    @Test
    @DisplayName("Should pull tasks in correct priority sequence and reduce queue size")
    void testPullingTasks() {
        Task task1 = new Task("1", "Task 1", Priority.LOW, LocalDate.now());
        Task task2 = new Task("2", "Task 2", Priority.HIGH, LocalDate.now());

        scheduler.addTask(task1);
        scheduler.addTask(task2);

        Task pulledFirst = scheduler.pull();
        assertEquals(task2, pulledFirst);

        Task pulledSecond = scheduler.pull();
        assertEquals(task1, pulledSecond);

        // queue should now be empty
        assertNull(scheduler.pull());
    }
}
