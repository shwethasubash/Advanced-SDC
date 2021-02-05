package dal.asd.catme;

import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.List;

public class POJOMock
{
    static IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    static IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();
    static ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();

    public static ArrayList<User> getUsers()
    {
        List<User> listOfUsers = new ArrayList<>();
        User user = accessControlModelAbstractFactory.makeUser();
        user.setBannerId("B00835717");
        user.setRole(new ArrayList<>());
        Role r = accessControlModelAbstractFactory.makeRole();
        r.setRoleId(String.valueOf(CatmeUtil.STUDENT_ROLE_ID));
        user.getRole().add(r);
        listOfUsers.add(user);
        User user1 = accessControlModelAbstractFactory.makeUser();
        user1.setBannerId("B00835718");
        user1.setRole(new ArrayList<>());
        listOfUsers.add(user1);

        return (ArrayList<User>) listOfUsers;

    }

    public static ArrayList<Course> getCourses()
    {
        List<Course> listOfCourses = new ArrayList<>();
        Course course = courseModelAbstractFactory.makeCourse();
        course.setCourseId("5409");
        course.setCourseName("Adv Web Development");
        listOfCourses.add(course);

        Course course1 = courseModelAbstractFactory.makeCourse();
        course1.setCourseId("5308");
        course1.setCourseName("DWDM");

        listOfCourses.add(course1);

        Course course2 = courseModelAbstractFactory.makeCourse();
        course2.setCourseId("5306");
        course2.setCourseName("Sofware Comprehension");

        listOfCourses.add(course2);

        return (ArrayList<Course>) listOfCourses;

    }

    public static List<String> getPublishedCourses()
    {
        List<String> publishedCourses = new ArrayList<>();

        publishedCourses.add("5308");
        return publishedCourses;
    }

    public static Course formCourse()
    {
        Course course = courseModelAbstractFactory.makeCourse();
        course.setCourseId("5306");
        course.setCourseName("Sofware Comprehension");
        return course;
    }

}
