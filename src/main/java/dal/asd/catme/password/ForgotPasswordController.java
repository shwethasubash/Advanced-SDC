package dal.asd.catme.password;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.*;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;

import static dal.asd.catme.util.CatmeUtil.ERROR_PAGE;
import static dal.asd.catme.util.CatmeUtil.RESET_PASSWORD_PAGE;

@Controller
@RequestMapping("/")
public class ForgotPasswordController
{
    IPasswordAbstractFactory passwordAbstractFactory = BaseAbstractFactoryImpl.instance().makePasswordAbstractFactory();
    IAccessControlAbstractFactory accessControlAbstractFactory = BaseAbstractFactoryImpl.instance().makeAccessControlAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeAccessControlModelAbstractFactory();
    private static final Logger log = LoggerFactory.getLogger(ForgotPasswordController.class);
    String bannerid;


    @RequestMapping("forgotPassword")
    public String forgotPassword()
    {
        log.info("Viewing forgot password page");
        return CatmeUtil.FORGOT_PASSWORD_PAGE;
    }

    @PostMapping("forgotPassword")
    public String resetPassword(@RequestParam("bannerid") String bannerid, Model model)
    {
        log.info("Resetting the password of the user");
        IPasswordResetService passwordResetService = passwordAbstractFactory.makePasswordResetService();
        IMailSenderService mailSenderService = accessControlAbstractFactory.makeMailSenderService();

        User u = passwordResetService.generateResetLink(bannerid);

        if (u == null)
        {
            model.addAttribute("message", "User does not exist");
            return CatmeUtil.FORGOT_PASSWORD_PAGE;
        }

        try
        {
            mailSenderService.sendResetLink(u);
            model.addAttribute("success", "Link Sent Successfully");
            return CatmeUtil.FORGOT_PASSWORD_PAGE;
        } catch (MessagingException e)
        {
            log.error("Error sending mail. Try again");
            model.addAttribute("message", "Error sending mail. Try again");
            return CatmeUtil.FORGOT_PASSWORD_PAGE;
        }
    }

    @GetMapping("reset-password")
    public String showPasswordResetPage(@RequestParam(name = "token") String token)
    {
        IPasswordResetService passwordResetService = passwordAbstractFactory.makePasswordResetService();
        bannerid = passwordResetService.validateToken(token);
        log.info("Resetting password and viewing reset page");
        if (bannerid == null)
        {
            System.out.println("Invalid Link");
            return ERROR_PAGE;
        }

        return RESET_PASSWORD_PAGE;
    }

    @PostMapping("reset-password")
    public String updatePassword(@RequestParam(name = "password") String password, Model model) throws PasswordException
    {
        log.info("Resetting password by updating with new password");
        IPasswordResetService passwordResetService = passwordAbstractFactory.makePasswordResetService();
        IPasswordPolicyCheckerService passwordPolicyCheckerService = passwordAbstractFactory.makePasswordPolicyCheckerService();

        User u = accessControlModelAbstractFactory.makeUser();
        u.setBannerId(bannerid);
        u.setPassword(password);

        try
        {
            if (passwordPolicyCheckerService.enforcePasswordPolicy(u) == false)
            {
                model.addAttribute("message", "Password Does not meet requirements");
                return RESET_PASSWORD_PAGE;
            }
        } catch (CatmeException e)
        {
            log.error("Updating password failed");
            e.printStackTrace();
        }

        try
        {
            passwordResetService.resetPassword(bannerid, password);
            model.addAttribute("message", "Password Reset Successfully");
            return RESET_PASSWORD_PAGE;
        } catch (CatmeException e)
        {
            model.addAttribute("message", e.getMessage());
            return RESET_PASSWORD_PAGE;
        }
    }
}