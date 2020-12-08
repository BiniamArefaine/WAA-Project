package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.javaMailApi.SendEmailClass;
import edu.miu.cs.auctionproject.service.UserService;
import edu.miu.cs.auctionproject.verificationAPI.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/user"})
@SessionAttributes("email, userObject")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = {"/add"})
    public String addNewUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            System.out.println("-----------");
            return "adduser";
        }
        // save product here
        System.out.println("----------5-");
        model.addAttribute("email", user.getEmail());
        model.addAttribute("userObject", user);
        sendEmailTo(user.getEmail(),model);
        userService.saveUser(user);
        return "Verification";
    }

    @GetMapping(value = "/resend_email")
    public String reSendEmailTo(String email, Model model) throws Exception {
        Object userEmail = model.getAttribute("email");
        System.out.println("inside ------------ email" + email);
        if (userEmail != null) {
            SendEmailClass.sendMailTo(userEmail.toString());
        }
        return "Verification";
    }

    @GetMapping(value = "/send_email")
    public String sendEmailTo(String email, Model model) throws Exception {
        Object userEmail = model.getAttribute("email");
        System.out.println("inside ------------ email" + email);
        if (userEmail != null) {
            SendEmailClass.sendMailTo(userEmail.toString());
//            model.addAttribute("user", new User());
            System.out.println("-------sendemail");
        }
        return "Verification";
    }

    @GetMapping(value = {"/get/{id}"})
    public Optional<User> getUserbyId(@PathVariable(value = "id") Long id){
        return userService.findUserById(id);
    }
    @DeleteMapping(value = {"/delete/{id}"})
    public String deleteUserById(@PathVariable(value="id")Long id){
        userService.deleteUser(id);
        return "redirect:/user/getall";

    }

    @GetMapping(value = {"/getall"})
    public String getAllUsers(Model model){
        System.out.println("----get all------");
        model.addAttribute("listUsers",userService.findAllUsers());
       return "listusers";
    }

    @GetMapping(value = {"edit/{userId}"})
    public String editUser(@PathVariable long userId, Model model) {
        Optional<User> user = userService.findUserById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "/edituserform";
        }
        return "/getall";
    }

    @GetMapping(value = {"/verify/{userId}"})
    public String verifyUser(@PathVariable long userId, Model model) {
        if(userService.findUserById(userId).isPresent()){
            User user = userService.findUserById(userId).get();
            if(Verification.verify(user.getFirstName(), user.getLicenceNumber())) {
                user.setVerification(true);
                model.addAttribute("user", user);
                return "forward:/auction/admin/listUsers";
            }
            else
                return "redirect:/user/delete/";
            }

        else

    {
        System.out.println("The user is not availbale");
    }
        return "forward:/auction/admin/listUsers";
        };




    @PostMapping(value = {  "/edit"})
    public String updateUser(@Validated @ModelAttribute("user") User user,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/edituserform";
        }
        userService.saveUser(user);
        return "redirect:/getall";
    }
    //biniam
    @RequestMapping(value = {"/adduser" })
    public String inputProduct(@ModelAttribute("users") User user, Model model) {
        return "adduser";
    }
    //new Verification
    @RequestMapping(value = {"/get_verified"})
    public String verify(@ModelAttribute String str, @RequestParam(value = "pin", required = false)  String pin, BindingResult bindingResult, Model model){
        System.out.println("pin-------" + Integer.parseInt(pin));
        int pinEntered = Integer.parseInt(pin);
        Object emailPin = model.getAttribute("pinCode");
        assert emailPin != null;
        //to be modified
        if(pinEntered == 1){
            System.out.println("yes-----------");
            return "Home";
        };
         return "Verification";
    }

    //
    @GetMapping(value = "/check_verification")
    public boolean checkVerification(String string){

        return true;
    }
}
