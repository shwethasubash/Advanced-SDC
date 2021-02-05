package dal.asd.catme.courses;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class CourseDaoImpl implements ICourseDao
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();

    public CourseDaoImpl()
    {
    }

    private static final Logger log = LoggerFactory.getLogger(CourseDaoImpl.class);

    IDatabaseAccess database;
    ICourseModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeCourseModelAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeAccessControlModelAbstractFactory();

    @Override
    public List<Course> getCourses(String role) throws CatmeException
    {
        log.info("Fetching all courses related to User");

        List<Course> listOfCourses = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try
        {
            database = databaseAbstractFactory.makeDatabaseAccess();

            log.info("DB connection is succesfull");
            String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

            switch (role)
            {
                case CatmeUtil.GUEST_ROLE:
                    log.info("Fetching courses of User " + currentUser + ": GUEST");
                    statement = database.getPreparedStatement(SELECT_GUEST_COURSES_QUERY);
                    resultSet = database.executeForResultSet(statement);
                    break;

                case CatmeUtil.TA_ROLE:
                    log.info("Fetching courses of User " + currentUser + ": TA");
                    statement = database.getPreparedStatement(SELECT_STUDENT_INSTRUCTOR_COURSE);
                    statement.setString(1, currentUser);
                    resultSet = database.executeForResultSet(statement);
                    break;

                case CatmeUtil.INSTRUCTOR_ROLE:
                    log.info("Fetching courses of User " + currentUser + ": INSTRUCTOR");
                    statement = database.getPreparedStatement(SELECT_INSTRUTOR_COURSES_QUERY);
                    statement.setString(1, currentUser);
                    resultSet = database.executeForResultSet(statement);
                    break;

                case CatmeUtil.STUDENT_ROLE:
                    log.info("Fetching courses of User " + currentUser + ": STUDENT");
                    statement = database.getPreparedStatement(SELECT_STUDENT_COURSES_QUERY);
                    statement.setString(1, currentUser);
                    resultSet = database.executeForResultSet(statement);
                    break;

                default:
                    break;
            }

            while (resultSet.next())
            {
                log.info("Fetched all courses from Database");
                Course course = modelAbstractFactory.makeCourse();
                course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID_FIELD));
                course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME_FIELD));

                listOfCourses.add(course);
            }
        } catch (SQLException | NullPointerException e)
        {
            throw new CatmeException("Failed while connecting and fetching courses from DB " + e.getMessage());
        } finally
        {
            database.cleanUp();
        }

        return listOfCourses;
    }

    @Override
    public List<Course> getAllCourses()
    {
        PreparedStatement statement = null;
        List<Course> courses = new ArrayList<>();

        database = databaseAbstractFactory.makeDatabaseAccess();
        try
        {
            statement = database.getPreparedStatement(SELECT_COURSE);
            ResultSet resultSet = database.executeForResultSet(statement);
            while (resultSet.next())
            {
                Course course = modelAbstractFactory.makeCourse();
                course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID));
                course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME));
                courses.add(course);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            database.cleanUp();
        }
        return courses;
    }

    @Override
    public Course displayCourseById(String courseId) throws CatmeException
    {
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        database = databaseAbstractFactory.makeDatabaseAccess();
        Course course = modelAbstractFactory.makeCourse();
        try
        {
            statement = database.getPreparedStatement(SELECT_COURSE_QUERY);
            statement.setString(1, courseId);
            resultSet = database.executeForResultSet(statement);


            while (resultSet.next())
            {
                course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID_FIELD));
                course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME_FIELD));
            }
        } catch (SQLException | NullPointerException e)
        {
            throw new CatmeException("Failed while connecting and fetching Course from DB " + e.getMessage());
        } finally
        {
            database.cleanUp();
        }

        return course;
    }

    @Override
    public String findRoleByCourse(User user, String courseId) throws CatmeException
    {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String role = "";

        database = databaseAbstractFactory.makeDatabaseAccess();
        try
        {
            statement = database.getPreparedStatement(SELECT_COURSE_ROLE_QUERY);
            statement.setString(1, user.getBannerId());
            statement.setString(2, courseId);
            resultSet = database.executeForResultSet(statement);
            while (resultSet.next())
            {
                role = resultSet.getString(CatmeUtil.ROLE_NAME_FIELD);
            }
        } catch (SQLException | NullPointerException e)
        {
            throw new CatmeException("Failed while connecting and Fetching Role for a Course in DB" + e.getMessage());
        } finally
        {
            database.cleanUp();
        }
        return role;
    }

    @Override
    public ArrayList<User> getRegisteredStudents(String courseId)
    {
        ArrayList<User> registeredStudents = new ArrayList<>();

        try
        {
            database = databaseAbstractFactory.makeDatabaseAccess();

            PreparedStatement stmt = database.getPreparedStatement(SEELCT_ENROLLED_STUDENTS_QUERY);
            stmt.setString(1, courseId);

            ResultSet rs = database.executeForResultSet(stmt);

            while (rs.next())
            {
                User u = accessControlModelAbstractFactory.makeUser();
                u.setBannerId(rs.getString(1));
                u.setLastName(rs.getString(2));
                u.setFirstName(rs.getString(3));
                u.setEmail("");
                registeredStudents.add(u);
            }

            return registeredStudents;

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } finally
        {
            database.cleanUp();
        }
        return null;
    }

    @Override
    public int checkCourseExists(String courseId)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(CHECK_COURSE_QUERY);
            stmt.setString(1, courseId);

            ResultSet rs = db.executeForResultSet(stmt);
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return rowCount;
    }

    @Override
    public int checkCourseRegistration(String bannerId, String courseId)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(CHECK_COURSE_REGISTRATION_QUERY);
            stmt.setString(1, bannerId);
            stmt.setString(2, courseId);
            ResultSet rs = db.executeForResultSet(stmt);
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return rowCount;
    }
}