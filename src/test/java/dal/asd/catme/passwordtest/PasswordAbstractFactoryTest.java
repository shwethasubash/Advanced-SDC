package dal.asd.catme.passwordtest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PasswordAbstractFactoryTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();

    @Test
    void makePasswordDaoTest()
    {
        IPasswordAbstractFactory passwordAbstractFactory = baseAbstractFactory.makePasswordAbstractFactory();
        assertNotNull(passwordAbstractFactory.makePasswordDao());
    }

    @Test
    void makePasswordPolicyCheckerServiceTest()
    {
        IPasswordAbstractFactory passwordAbstractFactory = baseAbstractFactory.makePasswordAbstractFactory();
        assertNotNull(passwordAbstractFactory.makePasswordPolicyCheckerService());
    }

    @Test
    void makePasswordResetServiceTest()
    {
        IPasswordAbstractFactory passwordAbstractFactory = baseAbstractFactory.makePasswordAbstractFactory();
        assertNotNull(passwordAbstractFactory.makePasswordResetService());
    }

    @Test
    void makePasswordRulesConfigTest()
    {
        IPasswordAbstractFactory passwordAbstractFactory = baseAbstractFactory.makePasswordAbstractFactory();
        assertNotNull(passwordAbstractFactory.makePasswordRulesConfig());
    }

}
