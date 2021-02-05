package dal.asd.catme.courses;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;

import static dal.asd.catme.util.DBQueriesUtil.STUDENT_ENROLL_QUERY;

public class StudentDaoImpl implements IStudentDao
{
    private static final Logger log = LoggerFactory.getLogger(StudentDaoImpl.class);
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();

    @Override
    public boolean enroll(User s, Course c)
    {
        log.info("Enrolling the student");
        IDatabaseAccess databaseAccess = databaseAbstractFactory.makeDatabaseAccess();
        try
        {
            PreparedStatement stmt = databaseAccess.getPreparedStatement(STUDENT_ENROLL_QUERY);
            stmt.setString(1, s.getBannerId());
            stmt.setString(2, c.getCourseId());

            stmt.executeUpdate();
            return true;
        } catch (Exception e)
        {
            log.error("Failed to enroll the student as the details are insufficient");
            e.printStackTrace();
            return false;
        } finally
        {
            databaseAccess.cleanUp();
        }
    }
}
