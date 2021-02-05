package dal.asd.catme.coursestest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourseDao;
import dal.asd.catme.util.CatmeUtil;

import java.util.List;

public class CourseDaoMock implements ICourseDao
{

    List<Course> listOfCourses;

    public CourseDaoMock(List<Course> listOfCourses)
    {
        this.listOfCourses = listOfCourses;
    }

    @Override
    public List<Course> getCourses(String role) throws CatmeException
    {
        if (role.equals(CatmeUtil.GUEST_ROLE) == false)
        {
            listOfCourses = null;
        }
        return listOfCourses;
    }

    @Override
    public List<Course> getAllCourses()
    {
        return POJOMock.getCourses();

    }

    @Override
    public Course displayCourseById(String courseId) throws CatmeException
    {
        for (Course c : listOfCourses)
        {
            if (c.getCourseId().equals(courseId))
            {
                return c;
            }
        }
        return null;
    }

    @Override
    public String findRoleByCourse(User user, String courseId) throws CatmeException
    {
        String role = "";

        if (user.getBannerId().equals("B00835822") && courseId.equals("5409"))
        {
            role = CatmeUtil.STUDENT_ROLE;
        }
        if (user.getBannerId().equals("B00835822") && courseId.equals("5308"))
        {
            role = CatmeUtil.TA_ROLE;
        }
        if (user.getBannerId().equals("B00835822") && courseId.equals("5306"))
        {
            role = CatmeUtil.GUEST_ROLE;
        }
        return role;
    }

    @Override
    public List<User> getRegisteredStudents(String courseId)
    {
        for (Course c : listOfCourses)
        {
            if (c.getCourseId().equals(courseId))
                return POJOMock.getUsers();
        }
        return null;
    }

    @Override
    public int checkCourseRegistration(String bannerId, String courseId)
    {
        for (Course c : listOfCourses)
        {
            if (c.getCourseId().equalsIgnoreCase(courseId))
            {
                for (User u : POJOMock.getUsers())
                {
                    if (u.getBannerId().equalsIgnoreCase(bannerId))
                    {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int checkCourseExists(String courseId)
    {
        for (Course c : listOfCourses)
        {
            if (c.getCourseId().equalsIgnoreCase(courseId))
                return 1;
        }
        return 0;
    }
}