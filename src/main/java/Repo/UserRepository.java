package Repo;

import Model.User;

import java.util.List;

public interface UserRepository {
    User logIn(User user) throws Exception;

    List<User> getUsers();

    void addUser(User user);

    void update(User user);

    void deleteUser(User user);
}
