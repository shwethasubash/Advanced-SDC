package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.User;

public interface IStudentDao
{
    boolean enroll(User u, Course c);
}
