package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Enrollment;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.courses.IRoleDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


public class EnrollTaServiceTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
    ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();

    @Test
    void enrollTa()
    {
        IRoleDao roleDaoMock = courseAbstractFactory.makeRoleDao();
        try
        {
            Enrollment enrollment = courseModelAbstractFactory.makeEnrollment();
            enrollment.setBannerId("B00835717");
            enrollment.setCourseId("5306");
            assertNotNull(roleDaoMock.assignTa(enrollment));

        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
}
