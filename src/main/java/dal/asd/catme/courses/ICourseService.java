package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.User;

import java.util.List;

public interface ICourseService
{
    List<Course> getCourses(String role) throws CatmeException;

    List<Course> getAllCourses();

    Course displayCourseById(String courseId) throws CatmeException;

    String findRoleByCourse(User user, String courseId) throws CatmeException;

    List<User> getEnrolledStudents(String courseId);
}
