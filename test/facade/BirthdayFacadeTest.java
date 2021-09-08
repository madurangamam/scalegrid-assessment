package facade;

import facade.impl.BirthDayFacadeImpl;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class BirthdayFacadeTest {

    private Set<User> userList;
    /*@Inject
    private BirthdayFacade birthdayFacade;*/
    private BirthdayFacade birthdayFacade = new BirthDayFacadeImpl();

    @Before
    public void setUp() {

        userList = new HashSet<>();

        User user1 = new User();
        LocalDateTime birthday1 = LocalDateTime.of(1980, 9, 9, 00, 00, 00);
        user1.setFirstName("Martin");
        user1.setLastName("Fowler");
        user1.setDateOfBirth(birthday1);

        User user2 = new User();
        LocalDateTime birthday2 = LocalDateTime.of(1978, 8, 8, 00, 00, 00);
        user2.setFirstName("Robert");
        user2.setLastName("C. Martin");
        user2.setDateOfBirth(birthday2);

        User user3 = new User();
        LocalDateTime birthday3 = LocalDateTime.of(1977, 8, 8, 00, 00, 00);
        user3.setFirstName("Grady");
        user3.setLastName("Booch");
        user3.setDateOfBirth(birthday3);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

    }

    @Test
    public void testFilterUserToSendEmail() {

        birthdayFacade.filterUserToSendEmail(userList, 2);

    }


}
