package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.UserDaoImpl;

public class PasswordAbstractFactoryImpl implements IPasswordAbstractFactory
{

    private final IPasswordDao passwordDao;
    private final IPasswordRulesConfig passwordRulesConfig;
    private final IPasswordResetService passwordResetService;
    private final IPasswordPolicyCheckerService passwordPolicyCheckerService;
    private final IUserDao userDao;

    public PasswordAbstractFactoryImpl()
    {
        passwordDao = new PasswordDaoImpl();
        userDao = new UserDaoImpl();
        passwordPolicyCheckerService = new PasswordPolicyCheckerImpl();
        passwordResetService = new PasswordResetServiceImpl(userDao, passwordDao);
        passwordRulesConfig = new PasswordRulesConfigImpl();
    }

    @Override
    public IPasswordDao makePasswordDao()
    {
        return passwordDao;
    }

    @Override
    public IPasswordPolicyCheckerService makePasswordPolicyCheckerService()
    {
        return passwordPolicyCheckerService;
    }

    @Override
    public IPasswordResetService makePasswordResetService()
    {
        return passwordResetService;
    }

    @Override
    public IPasswordRulesConfig makePasswordRulesConfig()
    {
        return passwordRulesConfig;
    }
}
