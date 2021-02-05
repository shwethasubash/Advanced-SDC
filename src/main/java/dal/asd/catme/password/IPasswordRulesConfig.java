package dal.asd.catme.password;

public interface IPasswordRulesConfig
{

    boolean validateMinimumLength(String currentPassword, int minimumLength) throws PasswordException;

    boolean validateMaximumLength(String currentPassword, int maximumLength) throws PasswordException;

    boolean validateMinimumUpperCase(String currentPassword, int mininmumUpperLength) throws PasswordException;

    boolean validateMinimumLowerCase(String currentPassword, int mininmumLowerLength) throws PasswordException;

    boolean validateMinimumSymbolsSpecialCharacters(String currentPassword, int minimumSymbolSpecialLength) throws PasswordException;

    boolean checkNotAllowedCharacters(String currentPassword, String regExpression) throws PasswordException;


}
