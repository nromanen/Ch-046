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

/**
 * Created by rmochetc on 01.02.2017.
 */

@Controller
public class ForgotPassController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/forgotPassword"}, method = RequestMethod.GET)
    public String forgotPassword() {
        return "forgotPass.jsp";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPass( @RequestParam("email") String email, Model model) throws MessagingException {

        User user = userService.getByMail(email);
//        User user = new User();

        if (user != null){
            System.out.println("Password = " + user.getPassword());
            model.addAttribute("email_send", "Your login and password send to e-mail");
            userService.sendEmail(user, "Your login is: " + user.getLogin() + ", your password is: " + user.getPassword());
            return "login.jsp";
        } else{
            model.addAttribute("error", "User whith hte same e-mail isn't in DB");
            return "forgotPass.jsp";
        }


    }
}
