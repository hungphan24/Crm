package service;

import model.UserModel;
import repository.UserRepository;

import java.util.List;

public class LoginService {
    private UserRepository userRepository = new UserRepository();
    public boolean checkLogin(String username, String password, Integer[] id) {

        List<UserModel> list = userRepository.findByEmailAndPassword(username, password, id);
        return list.size() > 0;
    }
}
