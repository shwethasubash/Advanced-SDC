package dal.asd.catme.passwordtest;

import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.password.IPasswordDao;

public class PasswordDaoMock implements IPasswordDao
{
    @Override
    public void resetPassword(User u) throws CatmeException
    {
        if (u.getPassword() == null)
            throw new CatmeException();

    }

    @Override
    public String readBannerIdFromToken(String token) throws CatmeException
    {
        if (token.equals("@@@@"))
        {
            return "B00000000";
        }
        return null;
    }

    @Override
    public void generatePasswordResetToken(User u, String token) throws CatmeException
    {

    }

    @Override
    public void removeToken(String bannerId)
    {

    }

    @Override
    public boolean matchWithPasswordHistory(String bannerId, String password) throws CatmeException
    {
        return false;
    }

    @Override
    public void deleteOverLimitPasswords(String bannerId) throws CatmeException
    {

    }
}