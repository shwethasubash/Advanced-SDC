package dal.asd.catme.courses;


public interface ICourseAbstractFactory
{
    ICourseService makeCourseService();

    IEnrollStudentService makeEnrollmentService();

    IRoleService makeRoleService();

    ICourseDao makeCourseDao();

    IStudentDao makeStudentDao();

    IRoleDao makeRoleDao();
}
