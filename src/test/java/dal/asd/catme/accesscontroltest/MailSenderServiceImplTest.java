package dal.asd.catme.accesscontroltest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.IMailSenderService;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.util.RandomTokenGenerator;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

import static dal.asd.catme.accesscontrol.MailSenderUtil.TOKEN_LENGTH;
import static org.junit.jupiter.api.Assertions.fail;

public class MailSenderServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IAccessControlAbstractFactory accessControlAbstractFactory = baseAbstractFactory.makeAccessControlAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();
    ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();

    @Test
    void sendMailTest()
    {

        User s = accessControlModelAbstractFactory.makeUser();
        s.setBannerId("B00101010");
        s.setFirstName("User");
        s.setLastName("Last");
        s.setEmail("test@mail.com");
        String
                sub = "This is subject of mail";
        String body =
                "You are registered in new course";

        IMailSenderService mailSenderService = accessControlAbstractFactory.makeMailSenderService();

        try
        {
            mailSenderService.sendMail(s, sub, body);
        } catch (MessagingException e)
        {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    void sendCredentialsToStudentTest()
    {
        IMailSenderService mailSenderService = accessControlAbstractFactory.makeMailSenderService();

        User s = accessControlModelAbstractFactory.makeUser();
        s.setBannerId("B00000000");
        s.setFirstName("Test");
        s.setPassword(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
        s.setEmail("test@mail.com");

        Course course = courseModelAbstractFactory.makeCourse();
        course.setCourseId("5308");

        try
        {
            mailSenderService.sendCredentialsToStudent(s, course);
        } catch (MessagingException e)
        {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void sendResetLinkTest()
    {
        IMailSenderService mailSenderService = accessControlAbstractFactory.makeMailSenderService();

        User s = accessControlModelAbstractFactory.makeUser();
        s.setBannerId("B00000000");
        s.setFirstName("Test");
        s.setPassword(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
        s.setEmail("test@mail.com");

        try
        {
            mailSenderService.sendResetLink(s);
        } catch (MessagingException e)
        {
            fail();
            e.printStackTrace();
        }
    }
}
