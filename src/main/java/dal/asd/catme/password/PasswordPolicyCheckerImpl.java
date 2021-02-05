package dal.asd.catme.password;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyCheckerImpl implements IPasswordPolicyCheckerService
{
    IPasswordRulesConfig passwordRulesConfig;

    private static final Logger log = LoggerFactory.getLogger(PasswordPolicyCheckerImpl.class);

    @Override
    public boolean enforcePasswordPolicy(User user) throws CatmeException, PasswordException
    {
        log.info("Enforcing password policies");
        boolean flag = true;
        passwordRulesConfig = BaseAbstractFactoryImpl.instance().makePasswordAbstractFactory().makePasswordRulesConfig();

        List<Boolean> validationResult = new ArrayList<>();
        if (PasswordRulesUtil.MINIMUM_LENGTH_ENABLE)
        {
            log.info("Enforcing MINIMUM_LENGTH_ENABLE");
            validationResult.add(passwordRulesConfig.validateMinimumLength(user.getPassword(), PasswordRulesUtil.MINIMUM_LENGTH));
        }
        if (PasswordRulesUtil.MAXIMUM_LENGTH_ENABLE)
        {
            log.info("Enforcing MAXIMUM_LENGTH_ENABLE");
            validationResult.add(passwordRulesConfig.validateMaximumLength(user.getPassword(), PasswordRulesUtil.MAXIMUM_LENGTH));
        }
        if (PasswordRulesUtil.MINIMUM_LOWER_CASE_ENABLE)
        {
            log.info("Enforcing MINIMUM_LOWER_CASE_ENABLE");
            validationResult.add(passwordRulesConfig.validateMinimumLowerCase(user.getPassword(), PasswordRulesUtil.MINIMUM_LOWER_CASE_LENGTH));
        }
        if (PasswordRulesUtil.MINIMUM_UPPER_CASE_ENABLE)
        {
            log.info("Enforcing MINIMUM_UPPER_CASE_ENABLE");
            validationResult.add(passwordRulesConfig.validateMinimumUpperCase(user.getPassword(), PasswordRulesUtil.MINIMUM_UPPER_CASE_LENGTH));
        }
        if (PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_ENABLE)
        {
            log.info("Enforcing MINIMUM_SYMBOL_SPECIAL_ENABLE");
            validationResult.add(passwordRulesConfig.validateMinimumSymbolsSpecialCharacters(user.getPassword(), PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_LENGTH));
        }
        if (PasswordRulesUtil.NOT_ALLOWED_CHARACTER_ENABLE)
        {
            log.info("Enforcing NOT_ALLOWED_CHARACTER_ENABLE");
            validationResult.add(passwordRulesConfig.checkNotAllowedCharacters(user.getPassword(), PasswordRulesUtil.NOT_ALLOWED_CHARACTER_REGEX));
        }
        if (validationResult.contains(false))
        {
            log.warn("Password validation failed");
            flag = false;
        }
        return flag;
    }
}
