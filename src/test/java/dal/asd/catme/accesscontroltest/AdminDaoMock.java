package dal.asd.catme.accesscontroltest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.IAdminDao;
import dal.asd.catme.courses.Course;
import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDaoMock implements IAdminDao
{

    @Override
    public int addCourse(Course course)
    {
        List<Course> courses = new ArrayList<>();
        if (courses.add(course))
        {
            return CatmeUtil.ONE;
        }
        return CatmeUtil.ZERO;
    }

    @Override
    public int deleteCourse(String courseId)
    {
        List<Course> courses = POJOMock.getCourses();
        Course course = courses.get(CatmeUtil.ZERO);
        if (course.getCourseId().equalsIgnoreCase(courseId))
        {
            courses.remove(CatmeUtil.ZERO);
            return CatmeUtil.ONE;
        }
        return CatmeUtil.ZERO;
    }

    @Override
    public int addInstructorToCourse(String user, String course)
    {
        Map<String, String> userCourseMapping = new HashMap<String, String>();
        if (userCourseMapping.put(course, user) == null)
        {
            return CatmeUtil.ONE;
        }
        return CatmeUtil.ZERO;
    }
}
