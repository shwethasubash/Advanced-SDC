package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseModelAbstractFactoryTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();

    @Test
    void makeCourseTest()
    {
        ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();
        assertNotNull(courseModelAbstractFactory.makeCourse());
    }

    @Test
    void makeEnrollmentTest()
    {
        ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();
        assertNotNull(courseModelAbstractFactory.makeEnrollment());
    }
}
