package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.User;


public interface IPasswordPolicyCheckerService
{
    boolean enforcePasswordPolicy(User user) throws CatmeException, PasswordException;
}
