package dal.asd.catme.accesscontrol;

public interface IAccessControlAbstractFactory
{
    IAdminDao makeAdminDao();

    IAdminService makeAdminService();

    IMailSenderService makeMailSenderService();

    IUserDao makeUserDao();

    IUserService makeUserService();
}
