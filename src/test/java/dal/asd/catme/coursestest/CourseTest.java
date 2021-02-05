package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();

    @Test
    public void getCourseIdTest()
    {
        Course c = courseModelAbstractFactory.makeCourse();

        c.setCourseId("5307");
        assertEquals(c.getCourseId(), "5307");
    }

    @Test
    public void setCourseIdTest()
    {
        Course c = courseModelAbstractFactory.makeCourse();

        c.setCourseId("5307");
        assertEquals(c.getCourseId(), "5307");
    }

    @Test
    public void getCourseNameTest()
    {
        Course c = courseModelAbstractFactory.makeCourse();

        c.setCourseName("ABCD");
        assertEquals(c.getCourseName(), "ABCD");
    }

    @Test
    public void setCourseNameTest()
    {
        Course c = courseModelAbstractFactory.makeCourse();

        c.setCourseName("ABCD");
        assertEquals(c.getCourseName(), "ABCD");
    }
}
