package dal.asd.catme.passwordtest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import dal.asd.catme.password.IPasswordRulesConfig;
import dal.asd.catme.password.PasswordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordRulesConfigTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IPasswordAbstractFactory passwordAbstractFactory = baseAbstractFactory.makePasswordAbstractFactory();

    @Test
    public void testvalidateMinimumLength() throws PasswordException
    {
        IPasswordRulesConfig passwordRulesConfig = passwordAbstractFactory.makePasswordRulesConfig();
        assertEquals(passwordRulesConfig.validateMinimumLength("PASSWORD", 8), true);
        assertEquals(passwordRulesConfig.validateMinimumLength("PASS", 8), false);
    }

    @Test
    public void testvalidateMaximumLength() throws PasswordException
    {
        IPasswordRulesConfig passwordRulesConfig = passwordAbstractFactory.makePasswordRulesConfig();
        assertEquals(passwordRulesConfig.validateMaximumLength("PASSWORD", 10), true);
        assertEquals(passwordRulesConfig.validateMaximumLength("PASSWORD1234", 10), false);
    }

    @Test
    public void testvalidateMinimumLowerCase() throws PasswordException
    {
        IPasswordRulesConfig passwordRulesConfig = passwordAbstractFactory.makePasswordRulesConfig();
        assertEquals(passwordRulesConfig.validateMinimumLowerCase("PassWORD", 3), true);
        assertEquals(passwordRulesConfig.validateMinimumLowerCase("PASSWOrd", 3), false);
    }

    @Test
    public void testvalidateMinimumUpperCase() throws PasswordException
    {
        IPasswordRulesConfig passwordRulesConfig = passwordAbstractFactory.makePasswordRulesConfig();
        assertEquals(passwordRulesConfig.validateMinimumUpperCase("PassWORD", 3), true);
        assertEquals(passwordRulesConfig.validateMinimumUpperCase("PassworD", 3), false);
    }

    @Test
    public void testvalidateMinimumSymbolsSpecialCharacters() throws PasswordException
    {
        IPasswordRulesConfig passwordRulesConfig = passwordAbstractFactory.makePasswordRulesConfig();
        assertEquals(passwordRulesConfig.validateMinimumSymbolsSpecialCharacters("P&as#sW**ORD%", 3), true);
        assertEquals(passwordRulesConfig.validateMinimumSymbolsSpecialCharacters("#PassworD&", 3), false);
    }

    @Test
    public void testcheckNotAllowedCharacters() throws PasswordException
    {
        IPasswordRulesConfig passwordRulesConfig = passwordAbstractFactory.makePasswordRulesConfig();
        assertEquals(passwordRulesConfig.checkNotAllowedCharacters("P&as#sW**ORD%", "[xyz]"), true);
        assertEquals(passwordRulesConfig.checkNotAllowedCharacters("#PassworD&", "[xyz]"), true);
        assertEquals(passwordRulesConfig.checkNotAllowedCharacters("#PassworD&x", "[xyz]"), false);
    }

}
