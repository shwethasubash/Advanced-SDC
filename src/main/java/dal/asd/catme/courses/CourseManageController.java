package dal.asd.catme.courses;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.*;
import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.studentlistimport.ICSVParser;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVReader;
import dal.asd.catme.studentlistimport.InvalidFileFormatException;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.RandomTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dal.asd.catme.accesscontrol.MailSenderUtil.TOKEN_LENGTH;

@Controller
public class CourseManageController
{
    ICSVParserAbstractFactory icsvParserAbstractFactory = BaseAbstractFactoryImpl.instance().makeCSVParserAbstractFactory();
    ICourseAbstractFactory courseAbstractFactory = BaseAbstractFactoryImpl.instance().makeCourseAbstractFactory();
    IAccessControlAbstractFactory accessControlAbstractFactory = BaseAbstractFactoryImpl.instance().makeAccessControlAbstractFactory();

    ICourseModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeCourseModelAbstractFactory();
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @RequestMapping("/manageCourse")
    public ModelAndView manageCourse(@RequestParam(name = "courseId") String courseId)
    {
        log.info("Manage Course " + courseId);
        ModelAndView uploadPage = new ModelAndView();

        try
        {
            CatmeSecurityConfig catmeSecurityConfig = SystemConfig.instance().getCatmeServiceConfig();
            List<String> roles = catmeSecurityConfig.fetchRolesHomePage();

            if (roles.contains(CatmeUtil.INSTRUCTOR_ROLE) || roles.contains(CatmeUtil.TA_ROLE))
            {
                log.info("Loading CSV File Upload Page");
                uploadPage.setViewName(CatmeUtil.MANAGE_COURSE_PAGE);

                uploadPage.addObject("courseId", courseId);
                ICourseService courseService = courseAbstractFactory.makeCourseService();
                uploadPage.addObject("studentList", courseService.getEnrolledStudents(courseId));
            } else
            {
                log.info("User is not TA or Instructor");
                log.info("Loading Login Page");
                uploadPage.setViewName(CatmeUtil.LOGIN_PAGE);
                return uploadPage;
            }

        } catch (CatmeException e)
        {
            e.printStackTrace();
        }

        return uploadPage;
    }

    @PostMapping("/manageCourse")
    public ModelAndView uploadFile(@RequestParam("student-list-csv") MultipartFile file, @RequestParam("courseId") String courseId)
    {
        ModelAndView model = new ModelAndView();
        model.setViewName(CatmeUtil.MANAGE_COURSE_PAGE);

        log.info("File Received at Server: " + file.getName());
        try
        {
            ICSVReader icsvReader = icsvParserAbstractFactory.makeCSVReader(file.getInputStream());
            ICSVParser icsvParser = icsvParserAbstractFactory.makeCSVParser();
            IUserService userService = accessControlAbstractFactory.makeUserService();
            IEnrollStudentService enrollStudentService = courseAbstractFactory.makeEnrollmentService();
            IMailSenderService mailSenderService = accessControlAbstractFactory.makeMailSenderService();

            icsvReader.validateFile(file);
            Course c = modelAbstractFactory.makeCourse();
            c.setCourseId(courseId);

            ArrayList<User> students = icsvParser.getStudentsFromFile(icsvReader);

            if (students == null)
            {
                model.addObject("message", "Error Enrolling Students");
                return model;
            }

            for (User s : students)
            {
                s.setPassword(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
                if (userService.addUser(s).equals(CatmeUtil.USER_CREATED))
                {
                    mailSenderService.sendCredentialsToStudent(s, c);
                }
            }

            if (enrollStudentService.enrollStudentsIntoCourse(students, c))
            {
                log.info("Students Enrolled");
                model.addObject("message", "Students Enrolled");
            } else
            {
                log.error("Error Enrolling Students... Check content of file");
                model.addObject("message", "Error Enrolling Students");
            }
        } catch (InvalidFileFormatException e)
        {
            log.error(e.getMessage());
            model.addObject("message", e.getMessage());
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (MessagingException e)
        {
            log.error(e.getMessage());
            model.addObject("message", e.getMessage());
        }
        return model;
    }
}
