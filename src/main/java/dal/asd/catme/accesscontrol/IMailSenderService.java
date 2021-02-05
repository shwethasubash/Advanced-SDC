package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;
import org.springframework.mail.MailException;

import javax.mail.MessagingException;

public interface IMailSenderService
{
    void sendMail(User user, String subject, String messageText) throws MailException, MessagingException;

    void sendCredentialsToStudent(User u, Course c) throws MessagingException;

    void sendResetLink(User u) throws MessagingException;

}
