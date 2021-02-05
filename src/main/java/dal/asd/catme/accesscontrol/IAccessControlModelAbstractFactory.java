package dal.asd.catme.accesscontrol;

public interface IAccessControlModelAbstractFactory
{
    Role makeRole();

    User makeUser();
}
