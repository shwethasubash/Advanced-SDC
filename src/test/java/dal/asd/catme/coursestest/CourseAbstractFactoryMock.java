package dal.asd.catme.coursestest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.courses.*;

public class CourseAbstractFactoryMock implements ICourseAbstractFactory
{
    private ICourseService courseService = null;
    private IEnrollStudentService enrollStudentService = null;
    private IRoleService roleService = null;
    private ICourseDao courseDao = null;
    private IStudentDao studentDao = null;
    private IRoleDao roleDao = null;

    @Override
    public ICourseService makeCourseService()
    {
        if (courseService == null)
        {
            courseService = new CourseServiceImpl(makeCourseDao());
        }
        return courseService;
    }

    @Override
    public IEnrollStudentService makeEnrollmentService()
    {
        if (enrollStudentService == null)
        {
            enrollStudentService = new EnrollStudentServiceImpl(makeRoleDao(), makeStudentDao());
        }
        return enrollStudentService;
    }

    @Override
    public IRoleService makeRoleService()
    {
        if (roleService == null)
        {
            roleService = new RoleServiceImpl(makeRoleDao());
        }
        return roleService;
    }

    @Override
    public ICourseDao makeCourseDao()
    {
        if (courseDao == null)
        {
            courseDao = new CourseDaoMock(POJOMock.getCourses());
        }
        return courseDao;
    }

    @Override
    public IStudentDao makeStudentDao()
    {
        if (studentDao == null)
        {
            studentDao = new StudentDaoMock();
        }
        return studentDao;
    }

    @Override
    public IRoleDao makeRoleDao()
    {
        if (roleDao == null)
        {
            roleDao = new RoleDaoMock(POJOMock.getUsers(), POJOMock.getCourses());
        }
        return roleDao;
    }
}
