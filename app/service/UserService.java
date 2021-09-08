package service;

import models.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {


    Optional<User> addUser(User user);
    Optional<User> getUser(int id);
    Set<User> getAllUsers();
    boolean sendEmail(int numOfHours);
}
