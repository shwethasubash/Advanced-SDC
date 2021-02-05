package dal.asd.catme.accesscontroltest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.*;

public class AccessControlAbstractFactoryMock implements IAccessControlAbstractFactory
{
    private IAdminDao adminDao = null;
    private IAdminService adminService = null;
    private IMailSenderService mailSenderService = null;
    private IUserDao userDao = null;
    private IUserService userService = null;

    @Override
    public IAdminDao makeAdminDao()
    {
        if (adminDao == null)
        {
            adminDao = new AdminDaoMock();
        }
        return adminDao;
    }

    @Override
    public IAdminService makeAdminService()
    {
        if (adminService == null)
        {
            adminService = new AdminServiceImpl(makeAdminDao());
        }
        return adminService;
    }

    @Override
    public IMailSenderService makeMailSenderService()
    {
        if (mailSenderService == null)
        {
            IAccessControlModelAbstractFactory accessControlModelAbstractFactory = BaseAbstractFactoryMock.instance().makeAccessControlModelAbstractFactory();
            User s = accessControlModelAbstractFactory.makeUser();
            s.setBannerId("B00101010");
            s.setFirstName("User");
            s.setLastName("Last");
            s.setEmail("test@mail.com");

            String
                    sub = "This is subject of mail";
            String body =
                    "You are registered in new course";

            mailSenderService = new MailSenderServiceImpl(new JavaMailSenderMock(s, sub, body));
        }
        return mailSenderService;
    }

    @Override
    public IUserDao makeUserDao()
    {
        if (userDao == null)
        {
            userDao = new UserDaoMock(POJOMock.getUsers());
        }
        return userDao;
    }

    @Override
    public IUserService makeUserService()
    {
        if (userService == null)
        {
            userService = new UserServiceImpl(makeUserDao(), null);
        }
        return userService;
    }
}
