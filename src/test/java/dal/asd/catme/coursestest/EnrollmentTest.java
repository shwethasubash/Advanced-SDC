package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Enrollment;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrollmentTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();

    @Test
    public void getBannerIdTest()
    {
        Enrollment e = courseModelAbstractFactory.makeEnrollment();

        e.setBannerId("B00121212");
        assertEquals(e.getBannerId(), "B00121212");
    }

    @Test
    public void setBannerIdTest()
    {
        Enrollment e = courseModelAbstractFactory.makeEnrollment();

        e.setBannerId("B00121212");
        assertEquals(e.getBannerId(), "B00121212");
    }

    @Test
    public void getCourseIdTest()
    {
        Enrollment e = courseModelAbstractFactory.makeEnrollment();

        e.setCourseId("5308");
        assertEquals(e.getCourseId(), "5308");
    }

    @Test
    public void setCourseIdTest()
    {
        Enrollment e = courseModelAbstractFactory.makeEnrollment();

        e.setCourseId("5308");
        assertEquals(e.getCourseId(), "5308");
    }
}
