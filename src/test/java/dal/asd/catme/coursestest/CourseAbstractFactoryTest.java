package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.ICourseAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseAbstractFactoryTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();

    @Test
    void makeCourseServiceTest()
    {
        ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
        assertNotNull(courseAbstractFactory.makeCourseService());
    }

    @Test
    void makeEnrollmentServiceTest()
    {
        ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
        assertNotNull(courseAbstractFactory.makeEnrollmentService());
    }

    @Test
    void makeRoleServiceTest()
    {
        ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
        assertNotNull(courseAbstractFactory.makeRoleService());
    }

    @Test
    void makeCourseDaoTest()
    {
        ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
        assertNotNull(courseAbstractFactory.makeCourseDao());
    }

    @Test
    void makeStudentDaoTest()
    {
        ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
        assertNotNull(courseAbstractFactory.makeStudentDao());
    }

    @Test
    void makeRoleDaoTest()
    {
        ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
        assertNotNull(courseAbstractFactory.makeRoleDao());
    }
}
