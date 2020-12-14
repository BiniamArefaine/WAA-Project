package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.FileUploadUtil;
import edu.miu.cs.auctionproject.domain.Address;
import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.service.AddressService;
import edu.miu.cs.auctionproject.service.CategoryService;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/new/{productId}" })
    public String inputAddress(@PathVariable long productId, Model model) {
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        Address address=addressService.findAddressByUserId(userId);
        httpSession.setAttribute("proId", productId);
        if(address==null) {
            model.addAttribute("address", new Address());
            return "address";
        }
        model.addAttribute("address", address);
        return "address";
    }



    @RequestMapping(value = {"/edit" })
    public String editAddress(Model model) {
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        Address address=addressService.findAddressByUserId(userId);
        if(address==null) {
            model.addAttribute("address", new Address());
            return "addressEdit";
        }
        model.addAttribute("address", address);
        return "addressEdit";
    }


    @RequestMapping(value = {"/update" })
    public String updateAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "addressEdit";
        }

        addressService.saveAddress(address);
        return "redirect:/product/getall";
    }



    @PostMapping(value = "/save")
    public String saveAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            return "address";
        }
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        user.setAddress(address);
        addressService.saveAddress(address);
        userService.saveUser(user);
        return "redirect:/product/payment/";
    }

    @PostMapping(value = "/seller/save")
    public String saveSellerAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "/secured/seller/address";
        }
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        user.setAddress(address);
        addressService.saveAddress(address);
//        userService.saveUser(user);
        List<Category>categories = categoryService.getAllCategories();
        model.addAttribute("product",new Product());
        model.addAttribute("categories", categories);
        model.addAttribute("depomessage","");
        return "/secured/seller/addproduct";
    }





}
