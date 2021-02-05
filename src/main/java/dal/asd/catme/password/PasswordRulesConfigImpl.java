package dal.asd.catme.password;

import dal.asd.catme.survey.SurveyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordRulesConfigImpl implements IPasswordRulesConfig
{

    private static final Logger log = LoggerFactory.getLogger(SurveyController.class);

    boolean regularExpressionMatcher(String currentPassword, String regularExpression)
    {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(currentPassword);
        return matcher.find();
    }

    @Override
    public boolean validateMinimumLength(String currentPassword, int minimumLength) throws PasswordException
    {

        Boolean flag = true;
        if (currentPassword == null)
        {
            log.error("Password is null");
            throw new PasswordException("Password is empty");
        } else
        {
            if (currentPassword.trim().length() < minimumLength)
            {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean validateMaximumLength(String currentPassword, int maximumLength) throws PasswordException
    {
        boolean flag = true;
        if (currentPassword == null)
        {
            log.error("Password is null");
            throw new PasswordException("Password is empty");
        } else
        {
            if (currentPassword.trim().length() > maximumLength)
            {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean validateMinimumUpperCase(String currentPassword, int mininmumUpperLength) throws PasswordException
    {

        Boolean flag = true;
        String minimumUpperCaseRegEx = PasswordRulesUtil.MINIMUM_UPPER_CASE_REGEX + "{" + mininmumUpperLength + "}";
        if (currentPassword == null)
        {
            log.error("Password is null");
            throw new PasswordException("Password is empty");
        } else
        {
            flag = regularExpressionMatcher(currentPassword, minimumUpperCaseRegEx);
        }
        return flag;
    }

    @Override
    public boolean validateMinimumLowerCase(String currentPassword, int mininmumLowerLength) throws PasswordException
    {
        boolean flag = true;
        String minimumLowerCaseRegEx = PasswordRulesUtil.MINIMUM_LOWER_CASE_REGEX + "{" + mininmumLowerLength + "}";
        if (currentPassword == null)
        {
            log.error("Password is null");
            throw new PasswordException("Password is empty");
        } else
        {
            flag = regularExpressionMatcher(currentPassword, minimumLowerCaseRegEx);
        }
        return flag;
    }

    @Override
    public boolean validateMinimumSymbolsSpecialCharacters(String currentPassword, int minimumSymbolSpecialLength) throws PasswordException
    {
        boolean flag = true;
        String minimumSymbolSpecialRegEx = PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_REGEX + "{" + minimumSymbolSpecialLength + "}";
        if (currentPassword == null)
        {
            log.error("Password is null");
            throw new PasswordException("Password is empty");
        } else
        {
            flag = regularExpressionMatcher(currentPassword, minimumSymbolSpecialRegEx);
        }
        return flag;
    }

    @Override
    public boolean checkNotAllowedCharacters(String currentPassword, String regExpression) throws PasswordException
    {
        boolean flag = true;
        String notAllowedCharactersRegEx = PasswordRulesUtil.NOT_ALLOWED_CHARACTER_REGEX;
        if (currentPassword != null)
        {
            log.error("Password is null");
            throw new PasswordException("Password is empty");
        } else
        {
            if (regularExpressionMatcher(currentPassword, notAllowedCharactersRegEx))
            {
                flag = false;
            }
        }
        return flag;
    }
}
