package dal.asd.catme.courses;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;

import java.sql.Connection;
import java.sql.SQLException;

public class RoleServiceImpl implements IRoleService
{
    IRoleDao roleDao;
    IDatabaseAccess db;

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();

    public RoleServiceImpl(IRoleDao roleDao)
    {
        this.roleDao = roleDao;
    }

    @Override
    public int assignTa(Enrollment user)
    {
        Connection con = null;
        try
        {
            db = databaseAbstractFactory.makeDatabaseAccess();
            con = db.getConnection();

            return (roleDao.assignTa(user));
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
        return 0;

    }
}
