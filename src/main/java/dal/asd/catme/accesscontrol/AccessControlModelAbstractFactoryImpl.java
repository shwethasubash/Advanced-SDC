package dal.asd.catme.accesscontrol;

public class AccessControlModelAbstractFactoryImpl implements IAccessControlModelAbstractFactory
{
    @Override
    public Role makeRole()
    {
        return new Role();
    }

    @Override
    public User makeUser()
    {
        return new User();
    }
}
