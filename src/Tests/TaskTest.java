// Kenneth Dandrow

package Tests;

import static org.junit.jupiter.api.Assertions.*;
import Task.Task;
import org.junit.jupiter.api.Test;

public class TaskTest {

    // Test for successful task creation
    @Test
    public void testTaskCreationSuccess() {
        Task task = new Task("1", "Task Name", "Task description");
        assertEquals("1", task.getTaskId());
        assertEquals("Task Name", task.getName());
        assertEquals("Task description", task.getDescription());
    }

    // Test for invalid task ID (too long)
    @Test
    public void testTaskIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "Task Name", "Task description");
        });
    }

    // Test for invalid task name (too long)
    @Test
    public void testNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "This task name is way too long", "Task description");
        });
    }

    // Test for invalid task description (too long)
    @Test
    public void testDescriptionTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "Task Name", "This task description is way too long to be valid because it exceeds fifty characters");
        });
    }

    // Test for null task ID
    @Test
    public void testTaskIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Task Name", "Task description");
        });
    }

    // Test for null task name
    @Test
    public void testNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", null, "Task description");
        });
    }

    // Test for null task description
    @Test
    public void testDescriptionNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("1", "Task Name", null);
        });
    }

    // Test for setter: valid name update
    @Test
    public void testSetNameSuccess() {
        Task task = new Task("1", "Task Name", "Task description");
        task.setName("Updated Task Name");
        assertEquals("Updated Task Name", task.getName());
    }

    // Test for setter: invalid name update (too long)
    @Test
    public void testSetNameTooLong() {
        Task task = new Task("1", "Task Name", "Task description");
        assertThrows(IllegalArgumentException.class, () -> task.setName("This task name is way too long"));
    }

    // Test for setter: valid description update
    @Test
    public void testSetDescriptionSuccess() {
        Task task = new Task("1", "Task Name", "Task description");
        task.setDescription("Updated Task Description");
        assertEquals("Updated Task Description", task.getDescription());
    }

    // Test for setter: invalid description update (too long)
    @Test
    public void testSetDescriptionTooLong() {
        Task task = new Task("1", "Task Name", "Task description");
        assertThrows(IllegalArgumentException.class, () -> task.setDescription("This task description is way too long to be valid because it exceeds fifty characters"));
    }

    // Test for boundary case: exact valid task name length (20 characters)
    @Test
    public void testSetNameBoundary() {
        Task task = new Task("1", "Valid Task Name Here", "Task description");
        task.setName("12345678901234567890");  // 20 characters
        assertEquals("12345678901234567890", task.getName());
    }

    // Test for boundary case: exact valid task description length (50 characters)
    @Test
    public void testSetDescriptionBoundary() {
        Task task = new Task("1", "Task Name", "Valid Task Description Here");
        task.setDescription("12345678901234567890123456789012345678901234567890");  // 50 characters
        assertEquals("12345678901234567890123456789012345678901234567890", task.getDescription());
    }
}
