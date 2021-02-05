package dal.asd.catme.accesscontrol;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseService;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("profile/")
public class ProfileController
{
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    ICourseService courseService;
    ICourseAbstractFactory courseAbstractFactory = BaseAbstractFactoryImpl.instance().makeCourseAbstractFactory();

    @RequestMapping("access")
    public String redirectHomePage()
    {
        log.info("Finding the Role of User and redirecting to respetive User's home page");
        return "redirect:/access";
    }

    @RequestMapping("student")
    public ModelAndView displayStudentHomePage() throws CatmeException
    {
        log.info("User is identified as a Student");
        ModelAndView modelAndView = new ModelAndView();

        courseService = courseAbstractFactory.makeCourseService();
        modelAndView.addObject("listOfCourses", courseService.getCourses(CatmeUtil.STUDENT_ROLE));
        modelAndView.setViewName(CatmeUtil.HOME_PAGE);
        return modelAndView;
    }

    @RequestMapping("ta")
    public ModelAndView displayTaHomePage() throws CatmeException
    {
        log.info("User is identified as a Student/TA");
        ModelAndView modelAndView = new ModelAndView();

        courseService = courseAbstractFactory.makeCourseService();
        modelAndView.addObject("listOfCourses", courseService.getCourses(CatmeUtil.TA_ROLE));
        modelAndView.setViewName(CatmeUtil.HOME_PAGE);
        return modelAndView;
    }

    @RequestMapping("instructor")
    public ModelAndView displayInstructorHomePage() throws CatmeException
    {
        log.info("User is identified as a Instructor");
        ModelAndView modelAndView = new ModelAndView();
        courseService = courseAbstractFactory.makeCourseService();
        modelAndView.addObject("listOfCourses", courseService.getCourses(CatmeUtil.INSTRUCTOR_ROLE));
        modelAndView.setViewName(CatmeUtil.HOME_PAGE);
        return modelAndView;
    }

    @RequestMapping("guest")
    public ModelAndView displayGuestHomePage() throws CatmeException
    {
        log.info("User is identified as Guest");
        ModelAndView modelAndView = new ModelAndView();
        courseService = courseAbstractFactory.makeCourseService();
        modelAndView.addObject("listOfCourses", courseService.getCourses(CatmeUtil.GUEST_ROLE));
        modelAndView.setViewName(CatmeUtil.HOME_PAGE);
        return modelAndView;
    }
}
