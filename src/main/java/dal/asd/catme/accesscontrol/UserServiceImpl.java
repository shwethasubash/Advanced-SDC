package dal.asd.catme.accesscontrol;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.password.IPasswordPolicyCheckerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService
{
    IDatabaseAccess db;
    IUserDao userDao;

    IPasswordPolicyCheckerService passwordPolicyCheckerService;

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();

    public UserServiceImpl(IUserDao userDao, IPasswordPolicyCheckerService passwordPolicyCheckerService)
    {
        this.userDao = userDao;
        this.passwordPolicyCheckerService = passwordPolicyCheckerService;
    }

    @Override
    public String addUser(User user)
    {
        String isUserAdded = "An account already exists with this BannerId.";
        Connection con = null;
        try
        {
            db = databaseAbstractFactory.makeDatabaseAccess();
            con = db.getConnection();

            if (passwordPolicyCheckerService.enforcePasswordPolicy(user))
            {
                if (1 == userDao.addUser(user))
                {
                    isUserAdded = "Successfully Signed Up.";
                }
            } else
            {
                isUserAdded = "weak password!!";
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (con != null)
            {
                try
                {
                    con.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return isUserAdded;
    }


    @Override
    public List<User> getUsers()
    {
        return userDao.getUsers();
    }

}
