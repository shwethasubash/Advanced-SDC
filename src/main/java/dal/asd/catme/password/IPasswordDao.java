package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.User;

public interface IPasswordDao
{

    void resetPassword(User u) throws CatmeException;

    String readBannerIdFromToken(String token) throws CatmeException;

    void generatePasswordResetToken(User u, String token) throws CatmeException;

    void removeToken(String bannerId);

    boolean matchWithPasswordHistory(String bannerId, String password) throws CatmeException;

    void deleteOverLimitPasswords(String bannerId) throws CatmeException;
}