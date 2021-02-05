package dal.asd.catme.passwordtest;

import dal.asd.catme.password.IPasswordRulesConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassordRulesConfigMock implements IPasswordRulesConfig
{

    public boolean regularExpressionMatcher(String currentPassword, String regularExpression)
    {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(currentPassword);
        return matcher.find();
    }

    public boolean validateMinimumLength(String currentPassword, int minimumLength)
    {

        Boolean flag = true;
        if (currentPassword != null)
        {
            if (currentPassword.trim().length() < minimumLength)
            {
                flag = false;
            }
        }
        return flag;
    }

    public boolean validateMaximumLength(String currentPassword, int maximumLength)
    {
        boolean flag = true;
        if (currentPassword != null)
        {
            if (currentPassword.trim().length() > maximumLength)
            {
                flag = false;
            }
        }
        return flag;
    }

    public boolean validateMinimumUpperCase(String currentPassword, int mininmumUpperLength)
    {

        Boolean flag = true;
        String minimumUpperCaseRegEx = "(.*[A-Z])" + "{" + mininmumUpperLength + "}";
        if (currentPassword != null)
        {
            if (!regularExpressionMatcher(currentPassword, minimumUpperCaseRegEx))
            {
                flag = false;
            }
        }
        return flag;
    }

    public boolean validateMinimumLowerCase(String currentPassword, int mininmumLowerLength)
    {
        boolean flag = true;
        String minimumLowerCaseRegEx = "(.*[a-z])" + "{" + mininmumLowerLength + "}";
        if (currentPassword != null)
        {
            if (!regularExpressionMatcher(currentPassword, minimumLowerCaseRegEx))
            {
                flag = false;
            }
        }
        return flag;
    }

    public boolean validateMinimumSymbolsSpecialCharacters(String currentPassword, int minimumSymbolSpecialLength)
    {
        boolean flag = true;
        String minimumSymbolSpecialRegEx = "(.*[$&+,:;=\\\\?@#|'<>.^*()%/])" + "{" + minimumSymbolSpecialLength + "}";
        if (currentPassword != null)
        {
            if (!regularExpressionMatcher(currentPassword, minimumSymbolSpecialRegEx))
            {
                flag = false;
            }
        }
        return flag;
    }

    public boolean checkNotAllowedCharacters(String currentPassword, String regExpression)
    {
        boolean flag = true;
        String notAllowedCharactersRegEx = "[xyz]";
        if (currentPassword != null)
        {
            if (regularExpressionMatcher(currentPassword, notAllowedCharactersRegEx))
            {
                flag = false;
            }
        }
        return flag;
    }


}
