package service;

import model.Task;
import model.UserModel;
import repository.TaskRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private TaskRepository taskRepository = new TaskRepository();
    public List<UserModel> getAllUser() {
        return userRepository.findAll();
    }

    public boolean insertUser(String email, String password, String fullname, int roleID) {
        return userRepository.insertUser(fullname, email, password, roleID);
    }

    public boolean deleteById(int id) {
        return userRepository.deleteById(id);
    }

    public UserModel FindById(int id) {
        return userRepository.FindById(id);
    }

    public List<Task> FindTaskByUserId(int id) {
        return taskRepository.FindTaskByUserId(id);
    }
}
