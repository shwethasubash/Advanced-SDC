package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseService;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CourseServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();

    @Test
    public void getCoursesTest() throws CatmeException
    {
        ICourseService courseServiceImpl = courseAbstractFactory.makeCourseService();
        assertNotNull(courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE));
    }

    @Test
    public void displayCourseByIdTest() throws CatmeException
    {
        ICourseService courseServiceImpl = courseAbstractFactory.makeCourseService();
        assertEquals("DWDM", courseServiceImpl.displayCourseById("5308").getCourseName());
        assertNotEquals("Adv Cloud", courseServiceImpl.displayCourseById("5308").getCourseName());
    }

    @Test
    public void getEnrolledStudentsTest() throws CatmeException
    {
        ICourseService courseServiceImpl = courseAbstractFactory.makeCourseService();
        assertNotNull(courseServiceImpl.getEnrolledStudents(POJOMock.getCourses().get(0).getCourseId()));
    }

    @Test
    public void displayCourseByIdNotFoundCheckTest() throws CatmeException
    {
        ICourseService courseServiceImpl = courseAbstractFactory.makeCourseService();
        assertNotNull(courseServiceImpl.displayCourseById("5308").getCourseName());
    }

    @Test
    public void findRoleByCourseTest() throws CatmeException
    {
        User user = accessControlModelAbstractFactory.makeUser();
        user.setBannerId("B00835822");
        ICourseService courseServiceImpl = courseAbstractFactory.makeCourseService();
        assertEquals(CatmeUtil.STUDENT_ROLE, courseServiceImpl.findRoleByCourse(user, "5409"));
        assertEquals(CatmeUtil.TA_ROLE, courseServiceImpl.findRoleByCourse(user, "5308"));
        assertEquals(CatmeUtil.GUEST_ROLE, courseServiceImpl.findRoleByCourse(user, "5306"));
        assertNotEquals(CatmeUtil.ADMIN_ROLE, courseServiceImpl.findRoleByCourse(user, "5306"));
    }

    @Test
    public void getAllCoursesTest()
    {
        ICourseService courseServiceImpl = courseAbstractFactory.makeCourseService();
        List<Course> courses = POJOMock.getCourses();

        assertEquals(courses.get(0).getCourseId(), courseServiceImpl.getAllCourses().get(0).getCourseId());
    }
}
