package dal.asd.catme.courses;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class RoleDaoImpl implements IRoleDao
{
    IUserDao userDao;
    ICourseDao courseDao;

    private static final Logger log = LoggerFactory.getLogger(RoleDaoImpl.class);
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();

    @Override
    public int assignRole(String bannerId, int roleId)
    {
        log.info("Assing a new role to the user");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int rs = 0;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(ASSIGN_ROLE_QUERY);
            stmt.setString(1, bannerId);
            stmt.setInt(2, roleId);

            rs = stmt.executeUpdate();
            return rs;
        } catch (Exception e)
        {
            log.error("Unable to assign a new role to the user");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
        return rs;
    }

    @Override
    public int addInstructor(String courseId, int userRoleId)
    {
        log.info("Adding an instrcutor to the course");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int rs = 0;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(INSERT_COURSE_INSTRUCTOR_QUERY);
            stmt.setString(1, courseId);
            stmt.setInt(2, userRoleId);

            rs = stmt.executeUpdate();
        } catch (Exception e)
        {
            log.error("Adding an instructor to course is failed");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
        return rs;
    }

    @Override
    public int checkCourseInstructor(String bannerId, String courseId)
    {
        log.info("Checking for an course instructor");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(CHECK_COURSE_INSTRUCTOR_QUERY);
            stmt.setString(1, bannerId);
            stmt.setString(2, courseId);

            ResultSet rs = db.executeForResultSet(stmt);
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            log.error("Failed while checking for an instructor");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return rowCount;
    }

    @Override
    public int checkUserRole(String bannerId, int roleId)
    {
        log.info("Finding the role of an user");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(CHECK_USER_ROLE);
            stmt.setString(1, bannerId);
            stmt.setInt(2, roleId);
            ResultSet rs = db.executeForResultSet(stmt);
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            log.error("Unable to find an user role");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return rowCount;

    }

    @Override
    public int getUserRoleId(String bannerId, int roleId)
    {
        log.info("Finding the role Id of current user");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int userRoleId = -1;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(GET_USER_ROLEID_QUERY);
            stmt.setString(1, bannerId);
            stmt.setInt(2, roleId);
            ResultSet rs = db.executeForResultSet(stmt);
            rs.next();
            userRoleId = rs.getInt(1);
        } catch (SQLException e)
        {
            log.error("Unable to find the role Id of current user");
            e.printStackTrace();
        }

        return userRoleId;

    }

    @Override
    public int assignTa(Enrollment user)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int result = 0;

        log.info("Assigning a TA to the course");
        try
        {
            userDao = baseAbstractFactory.makeAccessControlAbstractFactory().makeUserDao();
            if (0 != userDao.checkExistingUser(user.getBannerId()))
            {
                courseDao = BaseAbstractFactoryImpl.instance().makeCourseAbstractFactory().makeCourseDao();
                if (0 != courseDao.checkCourseExists(user.getCourseId()))
                {
                    if (0 == courseDao.checkCourseRegistration(user.getBannerId(), user.getCourseId()))
                    {
                        if (0 == checkCourseInstructor(user.getBannerId(), user.getCourseId()))
                        {
                            if (0 != checkUserRole(user.getBannerId(), CatmeUtil.TA_ROLE_ID))
                            {
                                int userRoleId = getUserRoleId(user.getBannerId(), CatmeUtil.TA_ROLE_ID);
                                result = addInstructor(user.getCourseId(), userRoleId);
                            } else
                            {
                                assignRole(user.getBannerId(), CatmeUtil.TA_ROLE_ID);
                                int userRoleId = getUserRoleId(user.getBannerId(), CatmeUtil.TA_ROLE_ID);
                                result = addInstructor(user.getCourseId(), userRoleId);
                            }
                            throw new EnrollmentException("The user is successfully assigned as TA.");

                        } else
                        {
                            throw new EnrollmentException("This user is already an instructor for this course.");
                        }
                    } else
                    {
                        throw new EnrollmentException("This user is currently registered in this course. Failed to assign.");
                    }
                } else
                {
                    throw new EnrollmentException("No course exists with this Course Id. Failed to assign.");
                }

            } else
            {
                throw new EnrollmentException("No user exists with this Banner Id. Failed to assign.");
            }
        } catch (Exception e)
        {
            log.info("Failed while assigning the TA");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
        return result;
    }
}