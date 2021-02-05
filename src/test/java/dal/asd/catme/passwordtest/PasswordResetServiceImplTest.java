package dal.asd.catme.passwordtest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import dal.asd.catme.password.IPasswordResetService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordResetServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IPasswordAbstractFactory passwordAbstractFactory = baseAbstractFactory.makePasswordAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();

    @Test
    void generateResetLinkTest()
    {
        IPasswordResetService service = passwordAbstractFactory.makePasswordResetService();

        assertNull(service.generateResetLink("ASDV"));
    }

    @Test
    void validateTokenTest()
    {
        IPasswordResetService service = passwordAbstractFactory.makePasswordResetService();

        assertNotNull(service.validateToken("@@@@"));

        assertNull(service.validateToken("!!!!"));
    }

    @Test
    void resetPasswordTest()
    {
        IPasswordResetService service = passwordAbstractFactory.makePasswordResetService();

        try
        {
            service.resetPassword("B00121212", "ABCD");
        } catch (CatmeException e)
        {
            e.printStackTrace();
        }
        try
        {
            service.resetPassword("B00121212", null);
            fail();
        } catch (CatmeException e)
        {

        }
    }
}