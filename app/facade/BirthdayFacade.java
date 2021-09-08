package facade;

import models.User;

import java.util.List;
import java.util.Set;

public interface BirthdayFacade {

    boolean filterUserToSendEmail(Set<User> userList, int hours);
}
