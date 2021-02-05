package dal.asd.catme.accesscontrol;


import dal.asd.catme.courses.Course;

public interface IAdminDao
{

    int addCourse(Course Course);

    int deleteCourse(String courseId);

    int addInstructorToCourse(String user, String course);

}
