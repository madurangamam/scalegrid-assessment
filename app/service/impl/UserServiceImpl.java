package service.impl;

import facade.BirthdayFacade;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BirthdayFacade birthdayFacade;
    private Map<Integer, User> users = new HashMap<>();

    @Override
    public Optional<User> addUser(User user) {
        int id = users.size();
        user.setId(id);
        users.put(id, user);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUser(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Set<User> getAllUsers() {
        return new HashSet<>(users.values());
    }

    @Override
    public boolean sendEmail(int numOfHours) {

        return birthdayFacade.filterUserToSendEmail(getAllUsers(), numOfHours);

    }
}
