package dal.asd.catme.password;

public interface IPasswordAbstractFactory
{
    IPasswordDao makePasswordDao();

    IPasswordPolicyCheckerService makePasswordPolicyCheckerService();

    IPasswordResetService makePasswordResetService();

    IPasswordRulesConfig makePasswordRulesConfig();
}
