package dal.asd.catme.accesscontrol;

import dal.asd.catme.password.IPasswordPolicyCheckerService;
import dal.asd.catme.password.PasswordPolicyCheckerImpl;

public class AccessControlAbstractFactoryImpl implements IAccessControlAbstractFactory
{
    private final IAdminDao adminDao;
    private final IUserDao userDao;

    private final IAdminService adminService;
    private final IMailSenderService mailSenderService;
    private final IUserService userService;
    private final IPasswordPolicyCheckerService passwordPolicyCheckerService;

    public AccessControlAbstractFactoryImpl()
    {
        adminDao = new AdminDaoImpl();
        userDao = new UserDaoImpl();

        adminService = new AdminServiceImpl(adminDao);
        mailSenderService = new MailSenderServiceImpl();
        passwordPolicyCheckerService = new PasswordPolicyCheckerImpl();
        userService = new UserServiceImpl(userDao, passwordPolicyCheckerService);
    }

    @Override
    public IAdminDao makeAdminDao()
    {
        return adminDao;
    }

    @Override
    public IAdminService makeAdminService()
    {
        return adminService;
    }

    @Override
    public IMailSenderService makeMailSenderService()
    {
        return mailSenderService;
    }

    @Override
    public IUserDao makeUserDao()
    {
        return userDao;
    }

    @Override
    public IUserService makeUserService()
    {
        return userService;
    }
}
