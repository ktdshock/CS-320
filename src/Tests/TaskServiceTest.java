// Kenneth Dandrow

package Tests;

import static org.junit.jupiter.api.Assertions.*;
import Task.Task;
import Task.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {
    private TaskService service;

    @BeforeEach
    public void setUp() {
        service = new TaskService();
    }

    // Test adding a task successfully
    @Test
    public void testAddTask() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        assertEquals("Test Task", service.getTask("1").getName());
    }

    // Test adding a duplicate task (same task ID)
    @Test
    public void testAddDuplicateTask() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(task); // Attempt to add the same task again
        });
    }

    // Test deleting a task successfully
    @Test
    public void testDeleteTask() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        service.deleteTask("1");
        assertNull(service.getTask("1")); // Check task is removed
    }

    // Test deleting a non-existent task
    @Test
    public void testDeleteNonExistentTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask("99"); // Task ID "99" doesn't exist
        });
    }

    // Test updating task name successfully (without updating description)
    @Test
    public void testUpdateTaskNameOnly() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        service.updateTask("1", "Updated Task", null); // Update only the name
        assertEquals("Updated Task", service.getTask("1").getName());
        assertEquals("This is a description", service.getTask("1").getDescription()); // Description remains the same
    }

    // Test updating task description successfully (without updating name)
    @Test
    public void testUpdateTaskDescriptionOnly() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        service.updateTask("1", null, "Updated Description"); // Update only the description
        assertEquals("Test Task", service.getTask("1").getName()); // Name remains the same
        assertEquals("Updated Description", service.getTask("1").getDescription());
    }

    // Test updating both task name and description
    @Test
    public void testUpdateTaskNameAndDescription() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        service.updateTask("1", "Updated Task", "Updated Description"); // Update both fields
        assertEquals("Updated Task", service.getTask("1").getName());
        assertEquals("Updated Description", service.getTask("1").getDescription());
    }

    // Test updating a non-existent task
    @Test
    public void testUpdateNonExistentTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTask("99", "Non-existent Task", null); // Task ID "99" doesn't exist
        });
    }

    // Test updating task with invalid (too long) name
    @Test
    public void testUpdateTaskWithInvalidName() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTask("1", "This name is way too long to be valid", null); // Invalid name (too long)
        });
    }

    // Test updating task with invalid (too long) description
    @Test
    public void testUpdateTaskWithInvalidDescription() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTask("1", null, "This description is way too long to be valid because it exceeds fifty characters");
        });
    }

    // Test boundary case: updating task name with exactly 20 characters
    @Test
    public void testUpdateTaskNameBoundary() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        service.updateTask("1", "12345678901234567890", null); // Name with exactly 20 characters
        assertEquals("12345678901234567890", service.getTask("1").getName());
    }

    // Test boundary case: updating task description with exactly 50 characters
    @Test
    public void testUpdateTaskDescriptionBoundary() {
        Task task = new Task("1", "Test Task", "This is a description");
        service.addTask(task);
        service.updateTask("1", null, "12345678901234567890123456789012345678901234567890"); // Description with exactly 50 characters
        assertEquals("12345678901234567890123456789012345678901234567890", service.getTask("1").getDescription());
    }
}
