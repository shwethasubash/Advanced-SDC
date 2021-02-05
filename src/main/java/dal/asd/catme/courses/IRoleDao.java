package dal.asd.catme.courses;


public interface IRoleDao
{
    int assignRole(String bannerId, int roleId);

    int addInstructor(String courseId, int userRoleId);

    int assignTa(Enrollment user);

    int checkCourseInstructor(String bannerId, String courseId);

    int checkUserRole(String bannerId, int roleId);

    int getUserRoleId(String bannerId, int roleId);

}