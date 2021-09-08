package facade.impl;

import exception.UserFormatException;
import facade.BirthdayFacade;
import models.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class BirthDayFacadeImpl implements BirthdayFacade {

    @Override
    public boolean filterUserToSendEmail(Set<User> userList, int hours) {

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime formattedToday = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 23, 59, 59);
        AtomicBoolean sendEmail = new AtomicBoolean(false);
        // get current dayOfMonth and month
        int dayOfMonth = today.getDayOfMonth();
        Month month = today.getMonth();

        userList.forEach(user -> {
            if (dayOfMonth == user.getDateOfBirth().getDayOfMonth() - 1 && month == user.getDateOfBirth().getMonth()) {
                LocalDateTime emailToBeSendTime = formattedToday.minusMinutes(hours * 60);
                if (emailToBeSendTime.isBefore(today)) {
                    sendEmail.set(true);
                    sendBirthdayWishEmail(user);
                }
            }

        });
        return sendEmail.get();
    }

    private void sendBirthdayWishEmail(User user) {
        /**
         * Email service should be called from here.
         */
        try {
            StringBuilder emailBuilder = new StringBuilder("Hello ");
            emailBuilder.append(user.getFirstName())
                    .append(" ").append(user.getLastName())
                    .append(" Wish you a Happy Birthday!");

            System.out.println(emailBuilder);

        } catch (NullPointerException e) {
            throw new UserFormatException("Exception occurs while retrieving user First Name or Last Name [user id]" + user.getId());
        }

    }


}
