package dal.asd.catme.accesscontroltest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccessControlAbstractFactoryTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();

    @Test
    void makeAdminDaoTest()
    {
        IAccessControlAbstractFactory accessControlAbstractFactory = baseAbstractFactory.makeAccessControlAbstractFactory();

        assertNotNull(accessControlAbstractFactory.makeAdminDao());
    }

    @Test
    void makeAdminServiceTest()
    {
        IAccessControlAbstractFactory accessControlAbstractFactory = baseAbstractFactory.makeAccessControlAbstractFactory();

        assertNotNull(accessControlAbstractFactory.makeAdminService());
    }

    @Test
    void makeMailSenderServiceTest()
    {
        IAccessControlAbstractFactory accessControlAbstractFactory = baseAbstractFactory.makeAccessControlAbstractFactory();

        assertNotNull(accessControlAbstractFactory.makeMailSenderService());
    }

    @Test
    void makeUserDaoTest()
    {
        IAccessControlAbstractFactory accessControlAbstractFactory = baseAbstractFactory.makeAccessControlAbstractFactory();

        assertNotNull(accessControlAbstractFactory.makeUserDao());
    }

    @Test
    void makeUserServiceTest()
    {
        IAccessControlAbstractFactory accessControlAbstractFactory = baseAbstractFactory.makeAccessControlAbstractFactory();

        assertNotNull(accessControlAbstractFactory.makeUserService());
    }
}
