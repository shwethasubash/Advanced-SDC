package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Enrollment;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.courses.IRoleService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
    ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();

    @Test
    public void assignTATest()
    {
        IRoleService roleService = courseAbstractFactory.makeRoleService();
        Enrollment enrollment = courseModelAbstractFactory.makeEnrollment();
        enrollment.setBannerId("B00835717");
        enrollment.setCourseId("5306");

        assertEquals(0, roleService.assignTa(enrollment));
    }
}
