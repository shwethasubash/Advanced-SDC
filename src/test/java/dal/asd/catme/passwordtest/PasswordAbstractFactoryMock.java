package dal.asd.catme.passwordtest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontroltest.UserDaoMock;
import dal.asd.catme.password.*;

public class PasswordAbstractFactoryMock implements IPasswordAbstractFactory
{
    private IPasswordDao passwordDao = null;
    private IPasswordPolicyCheckerService passwordPolicyCheckerService = null;
    private IPasswordResetService passwordResetService = null;
    private IPasswordRulesConfig passwordRulesConfig = null;

    @Override
    public IPasswordDao makePasswordDao()
    {
        if (passwordDao == null)
        {
            passwordDao = new PasswordDaoMock();
        }
        return passwordDao;
    }

    @Override
    public IPasswordPolicyCheckerService makePasswordPolicyCheckerService()
    {
        if (passwordPolicyCheckerService == null)
        {
            passwordPolicyCheckerService = new PasswordPolicyCheckerImpl();
        }
        return passwordPolicyCheckerService;
    }

    @Override
    public IPasswordResetService makePasswordResetService()
    {
        if (passwordResetService == null)
        {
            passwordResetService = new PasswordResetServiceImpl(new UserDaoMock(POJOMock.getUsers()), makePasswordDao());
        }
        return passwordResetService;
    }

    @Override
    public IPasswordRulesConfig makePasswordRulesConfig()
    {
        if (passwordRulesConfig == null)
        {
            passwordRulesConfig = new PassordRulesConfigMock();
        }
        return passwordRulesConfig;
    }
}
