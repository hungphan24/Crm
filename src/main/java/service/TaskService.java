package service;

import model.Task;
import repository.TaskRepository;

import java.util.List;

public class TaskService {
    private TaskRepository taskRepository = new TaskRepository();

    public List<Task> findAllTask() {
        return taskRepository.findAllTask();
    }
}
