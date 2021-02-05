package dal.asd.catme.accesscontrol;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.IRoleDao;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class UserDaoImpl implements IUserDao
{
    IRoleDao roleDao;

    PasswordEncoder p;

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
    IAccessControlModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();
    ICourseAbstractFactory courseAbstractFactory = baseAbstractFactory.makeCourseAbstractFactory();
    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public int checkExistingUser(String bannerId)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        log.info("Checking whether user already exists");
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(CHECK_EXISTING_USER_QUERY);
            stmt.setString(1, bannerId);

            ResultSet rs = db.executeForResultSet(stmt);
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            log.error("Error while checking for a user");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
        return rowCount;
    }

    @Override
    public int addUser(User user)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        log.info("Adding newly registered user");
        String bannerId = user.getBannerId();
        try
        {
            if (0 == checkExistingUser(bannerId))
            {
                p = SystemConfig.instance().getPasswordEncoder();
                PreparedStatement stmt = db.getPreparedStatement(ADD_USER_QUERY);
                stmt.setString(1, user.getBannerId());
                stmt.setString(2, user.getFirstName());
                stmt.setString(3, user.getLastName());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, p.encode(user.getPassword()));

                stmt.executeUpdate();
                roleDao = courseAbstractFactory.makeRoleDao();
                roleDao.assignRole(bannerId, CatmeUtil.GUEST_ROLE_ID);
                return 1;
            }
        } catch (Exception e)
        {
            log.error("Error while adding a new registered user");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return 0;
    }

    @Override
    public User getUser(String bannerId)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        log.info("Fetching the user details based on user id");
        try
        {
            PreparedStatement getUser = db.getPreparedStatement(GET_USER_QUERY);
            getUser.setString(1, bannerId);

            ResultSet rs = db.executeForResultSet(getUser);

            rs.next();
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);
            String emailid = rs.getString(4);

            User u = modelAbstractFactory.makeUser();
            u.setBannerId(bannerId);
            u.setFirstName(firstname);
            u.setLastName(lastname);
            u.setEmail(emailid);

            return u;
        } catch (Exception e)
        {
            log.error("Unable to fetch the user details");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return null;
    }

    @Override
    public List<User> getUsers()
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        log.info("Fetching all the users");
        List<User> users = new ArrayList<>();
        try
        {
            ResultSet resultSet = listUsers(LIST_USER_QUERY);

            while (resultSet.next())
            {
                User user = modelAbstractFactory.makeUser();
                user.setBannerId(resultSet.getString(CatmeUtil.BANNER_ID));
                user.setFirstName(resultSet.getString(CatmeUtil.FIRST_NAME));
                user.setLastName(resultSet.getString(CatmeUtil.LAST_NAME));
                users.add(user);
            }
        } catch (SQLException e)
        {
            log.error("Fetch all the users");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
        return users;
    }

    private ResultSet listUsers(String query)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        log.info("Fetching the list of users with student role");
        ResultSet rs = null;
        try
        {
            PreparedStatement preparedStatement = db.getPreparedStatement(query);
            preparedStatement.setString(1, CatmeUtil.STUDENT_ROLE);
            rs = db.executeForResultSet(preparedStatement);
        } catch (SQLException e)
        {
            log.error("Unable to list all the users (Students) in application");
            e.printStackTrace();
        }
        return rs;
    }
}