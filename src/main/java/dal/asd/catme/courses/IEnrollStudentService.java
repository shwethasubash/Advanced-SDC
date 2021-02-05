package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.User;

import java.util.ArrayList;

public interface IEnrollStudentService
{
    boolean enrollStudentsIntoCourse(ArrayList<User> students, Course c);

    void enrollStudent(User s, Course c) throws EnrollmentException;

    void assignStudentRole(User student) throws EnrollmentException;
}
