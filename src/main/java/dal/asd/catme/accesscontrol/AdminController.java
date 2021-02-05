package dal.asd.catme.accesscontrol;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.courses.ICourseService;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("admin/")
public class AdminController
{

    IAdminService adminServiceImpl;
    IUserService userService;

    ICourseModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeCourseModelAbstractFactory();
    ICourseAbstractFactory courseAbstractFactory = BaseAbstractFactoryImpl.instance().makeCourseAbstractFactory();
    IAccessControlAbstractFactory accessControlAbstractFactory = BaseAbstractFactoryImpl.instance().makeAccessControlAbstractFactory();

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("dashboard")
    public String adminPage()
    {
        logger.info("****Admin Controller - Admin Page Invoked*****");
        return CatmeUtil.ADMIN;
    }

    @RequestMapping("addCourse")
    public String addCourse(Model model)
    {
        logger.info("****Admin Controller - Add Course Invoked*****");
        model.addAttribute("course", modelAbstractFactory.makeCourse());
        return CatmeUtil.ADD_COURSE;
    }

    @PostMapping("addCourseToDatabase")
    public String addCourseToDatabase(@ModelAttribute Course course)
    {
        logger.info("****Admin Controller - Add Course to Database Invoked*****");
        adminServiceImpl = accessControlAbstractFactory.makeAdminService();
        int result = adminServiceImpl.addCourse(course);
        if (result == 1)
        {
            return CatmeUtil.SUCCESS_PAGE;
        } else if (result > 1)
        {
            return CatmeUtil.USER_PAGE;
        } else
        {
            return CatmeUtil.ERROR;
        }
    }

    @ModelAttribute("courses")
    public List<Course> getCourseList()
    {
        ICourseService courseService = courseAbstractFactory.makeCourseService();
        return courseService.getAllCourses();
    }

    @ModelAttribute("users")
    public List<User> getUsersList()
    {
        userService = accessControlAbstractFactory.makeUserService();
        return userService.getUsers();
    }

    @PostMapping(value = "deleteCourse")
    public String deleteCourse(@RequestParam String course)
    {
        logger.info("****Admin Controller - Delete Course Invoked*****");
        adminServiceImpl = accessControlAbstractFactory.makeAdminService();
        int result = adminServiceImpl.deleteCourse(course);
        if (result > 0)
        {
            return CatmeUtil.SUCCESS_PAGE;
        } else
        {
            return CatmeUtil.ERROR;
        }
    }

    @PostMapping("addInstructor")
    public String addInstructor(@RequestParam String course, @RequestParam String user)
    {
        logger.info("****Admin Controller - Add Instructor Invoked*****");
        adminServiceImpl = accessControlAbstractFactory.makeAdminService();
        int result = adminServiceImpl.addInstructorToCourse(user, course);
        if (result == 1)
        {
            return CatmeUtil.SUCCESS_PAGE;
        } else if (result > 1)
        {
            return CatmeUtil.USER_PAGE;
        } else
        {
            return CatmeUtil.ERROR;
        }
    }
}
