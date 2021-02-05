package dal.asd.catme.accesscontrol;

import java.util.List;

public interface IUserDao
{
    int checkExistingUser(String bannerId);

    int addUser(User user);

    User getUser(String bannerId);

    List<User> getUsers();

}
