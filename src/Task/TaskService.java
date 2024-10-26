// Kenneth Dandrow

package Task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private Map<String, Task> tasks = new HashMap<>();

    // Add Task
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Task ID already exists");
        }
        tasks.put(task.getTaskId(), task);
    }

    // Delete Task
    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID does not exist");
        }
        tasks.remove(taskId);
    }

    // Update Task fields
    public void updateTask(String taskId, String name, String description) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task ID does not exist");
        }

        // Update fields if values are provided
        if (name != null) task.setName(name);
        if (description != null) task.setDescription(description);
    }

    // Retrieve a task (for testing purposes)
    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }
}
