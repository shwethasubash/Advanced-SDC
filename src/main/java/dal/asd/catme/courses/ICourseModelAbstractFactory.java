package dal.asd.catme.courses;

public interface ICourseModelAbstractFactory
{
    Course makeCourse();

    Enrollment makeEnrollment();
}
