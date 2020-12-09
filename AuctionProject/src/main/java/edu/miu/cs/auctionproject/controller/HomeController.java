package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/","/home"})
    public String welcome(){
        return "Home";
    }

    @RequestMapping(value = {"/auction/secured/service"})
    public String service(){
        return "secured/services";
    }

    @RequestMapping(value = {"/auction/seller/index"})
    public String sellerService(){
        return "secured/seller/index";
    }

    @RequestMapping(value = {"/auction/customer/index"})
    public String customerService(){
        return "secured/customer/index";
    }

    @RequestMapping(value = {"/auction/admin/listUsers"})
    public String adminService(Model model){
        List<User> nonVerifiedUsers = userService.findAllNonVerifiedUsers();
        model.addAttribute("listUsers", nonVerifiedUsers);
        System.out.println(nonVerifiedUsers);
        return "secured/admin/listusers";
    }

    @RequestMapping(value = {"/auction/admin/manage_categories"})
    public String getManageCategories(){
        return "redirect:/categories/getall";
    }


}

