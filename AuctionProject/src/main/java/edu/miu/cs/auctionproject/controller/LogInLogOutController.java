package edu.miu.cs.auctionproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
