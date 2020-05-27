package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void deleteUser(Long id);
    void editUser(User user);
    List<User> listAllUsers();
    User getUserByLogin(String login);
    User getUserById(Long id);
}
