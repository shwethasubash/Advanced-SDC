package dal.asd.catme.accesscontroltest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAdminService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IAccessControlAbstractFactory accessControlAbstractFactory = baseAbstractFactory.makeAccessControlAbstractFactory();

    @Test
    public void deleteCourseTest()
    {
        IAdminService adminService = accessControlAbstractFactory.makeAdminService();

        assertEquals(1, adminService.deleteCourse(POJOMock.getCourses().get(0).getCourseId()));
    }

    @Test
    public void addCourseTest()
    {
        IAdminService adminService = accessControlAbstractFactory.makeAdminService();

        assertEquals(1, adminService.addCourse(POJOMock.getCourses().get(0)));
    }

    @Test
    public void addInstructorToCourseTest()
    {
        IAdminService adminService = accessControlAbstractFactory.makeAdminService();

        assertEquals(1, adminService.addInstructorToCourse(POJOMock.getUsers().get(0).getBannerId(), POJOMock.getCourses().get(0).getCourseId()));
    }
}
