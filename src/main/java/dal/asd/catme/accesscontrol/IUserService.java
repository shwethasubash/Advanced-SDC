package dal.asd.catme.accesscontrol;

import java.util.List;

public interface IUserService
{
    String addUser(User user);

    List<User> getUsers();

}
