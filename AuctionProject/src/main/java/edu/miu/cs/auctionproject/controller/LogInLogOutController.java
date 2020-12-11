package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogInLogOutController {

    @GetMapping(value = "/home/log_in")
    public String logInPage(){
        return "login/login";
    }


    @GetMapping(value = "/home/log_out")
    public String logOutPage(){
        return "login/login";
    }

    @GetMapping(value = "/home/forgot_password")
    public String forgotPassword(){
        return "redirect:/user/resetPassword";
    }
}
