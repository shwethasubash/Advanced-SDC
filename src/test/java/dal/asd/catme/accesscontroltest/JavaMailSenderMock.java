package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.User;
import org.springframework.mail.MailException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

public class JavaMailSenderMock extends JavaMailSenderImpl
{
    private final User u;
    private final String subject;
    private final String body;

    public JavaMailSenderMock(User u, String subject, String body)
    {
        this.u = u;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public MimeMessage createMimeMessage()
    {
        MimeMessage mimeMessage = new MimeMessage(Session.getInstance(new Properties()));
        return mimeMessage;
    }

    @Override
    public MimeMessage createMimeMessage(InputStream inputStream) throws MailException
    {
        return null;
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException
    {
        try
        {
            if (!u.getEmail().equals(mimeMessage.getRecipients(Message.RecipientType.TO)[0].toString()))
            {
                throw new MailPreparationException("Sender Address does not match");
            }
        } catch (MessagingException e)
        {
            throw new MailPreparationException("Error Creating MimeMessageHelper class");
        }

    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException
    {

    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException
    {

    }

    @Override
    public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException
    {

    }

    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException
    {

    }

    @Override
    public void send(SimpleMailMessage... simpleMailMessages) throws MailException
    {

    }
}
