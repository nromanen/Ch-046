package ua.cv.tim.controller;

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

/**
 * Created by rmochetc on 01.02.2017.
 */

@Controller
public class ForgotPassController {

    private final String CONTEXTPATH = "http://localhost:8080/travian/";
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
            model.addAttribute("error", "User with the same e-mail isn't in DB");
            return "forgotPass.jsp";
        }
        String token = UUID.randomUUID().toString();
        String url = CONTEXTPATH + "changePassword?id=" + user.getUuid() + "&token=" + token;
        String message = "To reset password click next link: " + url;
        userService.createPasswordResetTokenForUser(user, token);
        userService.sendEmail(user, message);

        model.addAttribute("email_send", "Link for restore password send to email");
        return "login.jsp";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(Model model, @RequestParam("id") String id, @RequestParam("token") String token) {
        User user = userService.getById(id);
        if (user != null && userService.isToken(user, token)){
            System.out.println("You can reset password!");
            System.out.println("id = " + id + ", token = " + token);
            model.addAttribute("id", id);
            return "updatePassword.jsp";
        }
        model.addAttribute("error", "Your link for restore password is incorrect!");
        return "forgotPass.jsp";
    }

    @RequestMapping(value = "/savePassword", method = RequestMethod.POST)
    public String savePassword(HttpServletRequest request, @RequestParam("password") String password, @RequestParam("password1") String password1, @RequestParam("id") String id, Model model) throws MessagingException {

        if(!password.equals(password1) && !checkPassword(password)){
            model.addAttribute("error", "Entered passwords are different");
            return "updatePassword.jsp";
        }
        User user = userService.getById(id);
        user.setPassword(password);
        userService.updateUserPassword(user);
        model.addAttribute("email_send", "Your password changed successfully");
        return "login.jsp";
    }

    private boolean checkPassword(String password){
        return false;
    }
}
