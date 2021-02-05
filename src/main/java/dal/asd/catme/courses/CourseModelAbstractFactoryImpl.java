package dal.asd.catme.courses;

public class CourseModelAbstractFactoryImpl implements ICourseModelAbstractFactory
{
    @Override
    public Course makeCourse()
    {
        return new Course();
    }

    @Override
    public Enrollment makeEnrollment()
    {
        return new Enrollment();
    }
}