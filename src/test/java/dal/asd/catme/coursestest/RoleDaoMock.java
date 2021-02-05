package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.*;
import dal.asd.catme.courses.IRoleDao;
import dal.asd.catme.courses.*;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoMock implements IRoleDao
{

    IUserDao userDao;
    ICourseDao courseDao;
    List<User> users;
    List<Course> courses;
    Course c;

    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = BaseAbstractFactoryMock.instance().makeAccessControlModelAbstractFactory();
    IAccessControlAbstractFactory accessControlAbstractFactory = BaseAbstractFactoryMock.instance().makeAccessControlAbstractFactory();
    ICourseAbstractFactory courseAbstractFactory = BaseAbstractFactoryMock.instance().makeCourseAbstractFactory();

    public RoleDaoMock(ArrayList<User> users, List<Course> courses)
    {
        this.users = users;
        this.courses = courses;
        this.c = courses.get(2);
        userDao = accessControlAbstractFactory.makeUserDao();
        courseDao = courseAbstractFactory.makeCourseDao();
    }


    @Override
    public int assignRole(String bannerId, int roleId)
    {
        for (User u : users)
        {
            if (u.getBannerId().equalsIgnoreCase(bannerId))
            {
                Role l = accessControlModelAbstractFactory.makeRole();
                l.setRoleId(String.valueOf(roleId));
                ArrayList<Role> roles = new ArrayList<>();
                roles.add(l);

                u.setRole(roles);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int addInstructor(String courseId, int userRoleId)
    {
        return 1;
    }

    @Override
    public int assignTa(Enrollment user)
    {
        if (1 == userDao.checkExistingUser(user.getBannerId()))
        {

            if (1 == courseDao.checkCourseExists(user.getCourseId()))
            {

                if (0 == courseDao.checkCourseRegistration(user.getBannerId(), user.getCourseId()))
                {

                    if (0 == checkCourseInstructor(user.getBannerId(), user.getCourseId()))
                    {
                        for (Course c : courses)
                        {
                            if (c.getCourseId().equalsIgnoreCase(user.toString()))
                            {

                                for (User u : users)
                                {
                                    if (u.getBannerId().equalsIgnoreCase(user.getBannerId()))
                                    {
                                        return 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int checkCourseInstructor(String bannerId, String courseId)
    {
        for (User i : POJOMock.getUsers())
        {
            if (i.getBannerId().equalsIgnoreCase(bannerId))
                return 1;
        }
        return 0;

    }

    @Override
    public int checkUserRole(String bannerId, int roleId)
    {
        for (User u : users)
        {
            if (u.getBannerId().equalsIgnoreCase(bannerId))
            {
                for (Role l : u.getRole())
                {
                    if (l.getRoleId().equals(String.valueOf(roleId)))
                        return 1;
                }
            }
        }
        return 0;

    }

    @Override
    public int getUserRoleId(String bannerId, int roleId)
    {
        return 1;
    }

}
