package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = {"/add"})
    public String addNewUser(@RequestBody @Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "adduser";
        }

        // save product here
        userService.saveUser(user);
        model.addAttribute("user", user);

        return "redirect:/user/getall";
    }

    @GetMapping(value = {"/get/{id}"})
    public Optional<User> getUserbyId(@PathVariable(value = "id") Long id){
        return userService.findUserById(id);
    }
    @DeleteMapping(value = {"/delete/{id}"})
    public void deleteUserById(@PathVariable(value="id")Long id){
        userService.deleteUser(id);
    }
    @GetMapping(value = {"/getall"})
    public List<User> getAllUsers(){
       return userService.findAllUsers();
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
//        List<User> users = userService.findAllUsers();
//        model.addAttribute("users", users);
        return "adduser";
    }
}
