package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceImpl implements IAdminService
{
    IAdminDao admin;

    public AdminServiceImpl(IAdminDao admin)
    {
        this.admin = admin;
    }

    @Override
    public int addCourse(Course course)
    {
        return admin.addCourse(course);
    }

    @Override
    public int deleteCourse(String courseId)
    {
        return admin.deleteCourse(courseId);
    }


    @Override
    public int addInstructorToCourse(String user, String course)
    {
        return admin.addInstructorToCourse(user, course);
    }
}
