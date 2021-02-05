package dal.asd.catme.password;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.util.RandomTokenGenerator;

import static dal.asd.catme.accesscontrol.MailSenderUtil.TOKEN_LENGTH;


public class PasswordResetServiceImpl implements IPasswordResetService
{
    IUserDao userDao;
    IPasswordDao passwordDao;

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();

    public PasswordResetServiceImpl(IUserDao userDao, IPasswordDao passwordDao)
    {
        this.userDao = userDao;
        this.passwordDao = passwordDao;
    }

    @Override
    public User generateResetLink(String bannerid)
    {
        try
        {
            if (userDao.checkExistingUser(bannerid) == 0)
            {
                return null;

            }
            User u = userDao.getUser(bannerid);

            if (u == null)
            {
                return null;
            }

            String token = RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH);

            u.setPassword(token);

            passwordDao.generatePasswordResetToken(u, token);

            return u;
        } catch (CatmeException e)
        {
            return null;
        }
    }

    @Override
    public String validateToken(String token)
    {
        String bannerId = null;
        try
        {
            bannerId = passwordDao.readBannerIdFromToken(token);

        } catch (CatmeException e)
        {
            e.printStackTrace();
        }
        return bannerId;
    }

    @Override
    public void resetPassword(String bannerId, String password) throws CatmeException
    {
        User u = accessControlModelAbstractFactory.makeUser();
        u.setBannerId(bannerId);
        u.setPassword(password);

        passwordDao.resetPassword(u);
        passwordDao.removeToken(bannerId);
    }
}
