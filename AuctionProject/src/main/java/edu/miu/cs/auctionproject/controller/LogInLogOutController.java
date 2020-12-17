package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LogInLogOutController {

    @Autowired
    UserService userService;


    @Autowired
    HttpSession httpSession;
    int counts = 3;
    @GetMapping(value = "/home/log_in")
    public String logInPage(){
        return "login/login";
    }

    @GetMapping(value = "/home/log_out")
    public String logOutPage() {
        return "login/login";
    }

    @GetMapping(value = "/home/forgot_password")
    public String forgotPassword() {
        return "redirect:/user/resetPassword";
    }
}
