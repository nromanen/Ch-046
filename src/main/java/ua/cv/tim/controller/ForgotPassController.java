package ua.cv.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.cv.tim.model.User;
import ua.cv.tim.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rmochetc on 01.02.2017.
 */

@Controller
public class ForgotPassController {

    private static final Logger logger = LoggerFactory.getLogger(ForgotPassController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/forgotPassword"}, method = RequestMethod.GET)
    public String forgotPassword() {
        return "forgotPass.jsp";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail, Model model) throws MessagingException {

        User user = userService.getByMail(userEmail);
        if (user == null) {
            logger.info("User with the same e-mail {} isn't in DB", userEmail);
            model.addAttribute("error", "User with the same e-mail isn't in DB");
            return "forgotPass.jsp";
        }
        String token = UUID.randomUUID().toString();

        String url = request.getRequestURL().toString().replace("forgotPassword", "changePassword") + "?id=" + user.getUuid() + "&token=" + token;
        String message = "To reset password click next link: " + url;
        userService.createPasswordResetTokenForUser(user, token);
        userService.sendEmail(user, message);

        logger.info("Link for restore password send to email {}", userEmail);
        model.addAttribute("email_send", "Link for restore password send to email");
        return "login.jsp";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(Model model, @RequestParam("id") String id, @RequestParam("token") String token) {
        User user = userService.getById(id);
        if (user != null && userService.isToken(user, token)){

            model.addAttribute("id", id);
            logger.info("Restore password for user with id: {}", id);
            return "updatePassword.jsp";
        }
        model.addAttribute("errorLink", "Your link for restore password is incorrect!");
        logger.info("Link for restore password is incorrect! id = {}, token = {}", id, token);
        return "forgotPass.jsp";
    }

    @RequestMapping(value = "/savePassword", method = RequestMethod.POST)
    public String savePassword(HttpServletRequest request, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, @RequestParam("id") String id, Model model) throws MessagingException {

        if(!password.equals(confirmPassword) && !checkPassword(password)){
            model.addAttribute("error", "Entered passwords are different");
            logger.info("Passwords is different! password = {}, confirmPassword = {}", password, confirmPassword);
            return "updatePassword.jsp";
        }
        User user = userService.getById(id);
        user.setPassword(password);
        userService.updateUserPassword(user);
        model.addAttribute("pass_change", "Your password changed successfully");
        logger.info("Your password changed successfully fot user with ig = {}", id);
        return "login.jsp";
    }

    private boolean checkPassword(String password){
            String ePattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%_*#?&])[A-Za-z\\d$@_!%*#?&]{8,32}$";
            Pattern p = java.util.regex.Pattern.compile(ePattern);
            Matcher m = p.matcher(password);
            return m.matches();
    }
}
