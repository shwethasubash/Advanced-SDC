package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static dal.asd.catme.accesscontrol.MailSenderUtil.*;

public class MailSenderServiceImpl implements IMailSenderService
{
    private final JavaMailSenderImpl mailSender;

    public MailSenderServiceImpl(JavaMailSenderImpl mailSender)
    {
        this.mailSender = mailSender;
    }

    private static final Logger log = LoggerFactory.getLogger(MailSenderServiceImpl.class);

    public MailSenderServiceImpl()
    {
        this.mailSender = new JavaMailSenderImpl();
        this.mailSender.setHost(HOST);
        this.mailSender.setPassword(PASSWORD);
        this.mailSender.setPort(PORT);
        this.mailSender.setUsername(USERNAME);

        Properties mailProperties = new Properties();

        mailProperties.put("mail.smtp.starttls.enable", STARTTLS_ENABLE);

        this.mailSender.setJavaMailProperties(mailProperties);
    }


    @Override
    public void sendMail(User user, String subject, String bodyText) throws MailException, MessagingException
    {
        //code taken from https://stackoverflow.com/questions/5289849/how-do-i-send-html-email-in-spring-mvc
        log.info("Sending mail to user");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setText(bodyText, true);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendCredentialsToStudent(User u, Course c) throws MessagingException
    {
        log.info("Send user credentials to all enrolled students");
        String bodyText = getFormattedEmailForNewStudent(u, c);

        if (bodyText == null)
            throw new MessagingException("Error Creating Mail Text From Template");

        String subject = NEW_STUDENT_EMAIL_SUBJECT;

        sendMail(u, subject, bodyText);
    }

    @Override
    public void sendResetLink(User u) throws MailException, MessagingException
    {
        log.info("Sending an reset password link to user");
        sendMail(u, FORGOT_PASSWORD_EMAIL_SUBJECT, getFormattedEmailForForgotPassword(u));
    }

    public String getFormattedEmailForNewStudent(User u, Course c)
    {
        log.info("Designing email format for a new student");
        try
        {
            File file = new File(PATH_TO_NEW_STUDENT_TEMPLATE);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, StandardCharsets.UTF_8);

            str = str.replace(TEMPLATE_USERNAME, u.getFirstName());
            str = str.replace(TEMPLATE_BANNERID, u.getBannerId());
            str = str.replace(TEMPLATE_PASSWORD, u.getPassword());
            str = str.replace(TEMPLATE_COURSE, c.getCourseId());

            return str;
        } catch (FileNotFoundException e)
        {
            log.error("File not found to upload");
            return null;
        } catch (UnsupportedEncodingException e)
        {
            return null;
        } catch (IOException e)
        {
            return null;
        }
    }

    public String getFormattedEmailForForgotPassword(User u)
    {
        log.info("Designing format for forgot password");
        try
        {
            File file = new File(PATH_TO_FORGOT_PASSWORD_TEMPLATE);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, StandardCharsets.UTF_8);

            str = str.replace(TEMPLATE_USERNAME, u.getFirstName());
            str = str.replace(TEMPLATE_RESETLINK, RESETLINK + u.getPassword());

            return str;
        } catch (FileNotFoundException e)
        {
            log.error("File not found to upload");
            return null;
        } catch (UnsupportedEncodingException e)
        {
            return null;
        } catch (IOException e)
        {
            return null;
        }
    }
}
