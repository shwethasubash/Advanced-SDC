package dal.asd.catme.courses;

public class CourseAbstractFactoryImpl implements ICourseAbstractFactory
{
    private final ICourseDao courseDao;
    private final ICourseService courseService;
    private final IRoleDao roleDao;
    private final IRoleService roleService;
    private final IStudentDao studentDao;
    private final IEnrollStudentService enrollStudentService;

    public CourseAbstractFactoryImpl()
    {
        courseDao = new CourseDaoImpl();
        roleDao = new RoleDaoImpl();
        studentDao = new StudentDaoImpl();

        courseService = new CourseServiceImpl(courseDao);
        roleService = new RoleServiceImpl(roleDao);
        enrollStudentService = new EnrollStudentServiceImpl(roleDao, studentDao);
    }

    @Override
    public ICourseService makeCourseService()
    {
        return courseService;
    }

    @Override
    public IEnrollStudentService makeEnrollmentService()
    {
        return enrollStudentService;
    }

    @Override
    public IRoleService makeRoleService()
    {
        return roleService;
    }

    @Override
    public ICourseDao makeCourseDao()
    {
        return courseDao;
    }

    @Override
    public IStudentDao makeStudentDao()
    {
        return studentDao;
    }

    @Override
    public IRoleDao makeRoleDao()
    {
        return roleDao;
    }
}
