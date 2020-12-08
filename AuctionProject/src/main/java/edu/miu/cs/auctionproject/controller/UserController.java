package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.FileUploadUtil;
import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
//import edu.miu.cs.auctionproject.javaMailApi.SendEmailClass;
import edu.miu.cs.auctionproject.service.CategoryService;
import edu.miu.cs.auctionproject.service.ProductService;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/user"})
@SessionAttributes("email, userObject")

public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
//    @PostMapping(value = {"/add"})
//    public String addNewUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) throws Exception {
//        if (bindingResult.hasErrors()) {
//            System.out.println("-----------");
//            return "adduser";
//        }
//        // save product here
//        System.out.println("----------5-");
//        model.addAttribute("email", user.getEmail());
//        model.addAttribute("userObject", user);
//        sendEmailTo(user.getEmail(),model);
//        userService.saveUser(user);
//        return "Verification";
//    }
//
//    @GetMapping(value = "/resend_email")
//    public String reSendEmailTo(String email, Model model) throws Exception {
//        Object userEmail = model.getAttribute("email");
//        System.out.println("inside ------------ email" + email);
//        if (userEmail != null) {
//            SendEmailClass.sendMailTo(userEmail.toString());
//        }
//        return "Verification";
//    }
//
//    @GetMapping(value = "/send_email")
//    public String sendEmailTo(String email, Model model) throws Exception {
//        Object userEmail = model.getAttribute("email");
//        System.out.println("inside ------------ email" + email);
//        if (userEmail != null) {
//            SendEmailClass.sendMailTo(userEmail.toString());
////            model.addAttribute("user", new User());
//            System.out.println("-------sendemail");
//        }
//        return "Verification";
//    }
//
//    @GetMapping(value = {"/get/{id}"})
//    public Optional<User> getUserbyId(@PathVariable(value = "id") Long id){
//        return userService.findUserById(id);
//    }
//    @DeleteMapping(value = {"/delete/{id}"})
//    public void deleteUserById(@PathVariable(value="id")Long id){
//        userService.deleteUser(id);
//    }
//
//    @GetMapping(value = {"/getall"})
//    public String getAllUsers(Model model){
//        System.out.println("----get all------");
//        model.addAttribute("listUsers",userService.findAllUsers());
//       return "listusers";
//    }
//    @GetMapping(value = {"edit/{userId}"})
//    public String editUser(@PathVariable long userId, Model model) {
//        Optional<User> user = userService.findUserById(userId);
//        if (user.isPresent()) {
//            model.addAttribute("user", user.get());
//            return "/edituserform";
//        }
//        return "/getall";
//    }
//
//    @PostMapping(value = {  "/edit"})
//    public String updateUser(@Validated @ModelAttribute("user") User user,
//                                 BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("errors", bindingResult.getAllErrors());
//            return "/edituserform";
//        }
//        userService.saveUser(user);
//        return "redirect:/getall";
//    }
    //biniam
    @RequestMapping(value = {"/adduser" })
    public String inputProduct(@ModelAttribute("users") User user, Model model) {
        return "adduser";
    }
    //new Verification
//    @RequestMapping(value = {"/get_verified"})
//    public String verify(@ModelAttribute String str, @RequestParam(value = "pin", required = false)  String pin, BindingResult bindingResult, Model model){
//        System.out.println("pin-------" + Integer.parseInt(pin));
//        int pinEntered = Integer.parseInt(pin);
//        Object emailPin = model.getAttribute("pinCode");
//        if(pinEntered == Integer.parseInt(emailPin.toString())){
//            System.out.println("yes-----------");
//            return "Home";
//        };
//         return "Verification";
//    }

    //
    @GetMapping(value = "/check_verification")
    public boolean checkVerification(String string){

        return true;
    }

    // essey seller gets his products
    @RequestMapping(value={"/{userId}/getall"})
    public ModelAndView listProducts(@PathVariable long userId, ModelAndView modelAndView) {
        List<Product> products=userService.findAllProducts(userId);
        modelAndView.addObject("products",products);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("productsCount", products.size());
        modelAndView.setViewName("secured/seller/userproducts");
        return modelAndView;
    }


        @GetMapping(value = {"product/edit/{productId}"})
    public String editProduct(@PathVariable long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("categories",categoryService.getAllCategories());
            return "secured/seller/productEdit";
        }
        return "secured/seller/listproduct";
    }

    @PostMapping(value = {"product/edit"})
    public String updateProduct(@Validated @ModelAttribute("product") Product product,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "secured/seller/productEdit";
        }
        productService.saveProduct(product);
        return "redirect:/product/getall";
    }
    @RequestMapping(value = {"{id}/product/new" })
    public String inputProduct(@ModelAttribute("product") Product product,Model model) {
        List<Category>categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "secured/seller/addproduct";
    }

    @PostMapping(value = "product/add")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile,
                              Model model) throws IOException {
        System.out.println("kffkdsfjkdsafjkdsaf");
        if (bindingResult.hasErrors()) {
            return "secured/seller/addproduct";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPhotos(fileName);
        System.out.println(product.getPhotos());
        String uploadDir = "src/main/resources/static/images/product-photos/" + product.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        Optional<User> users=userService.findUserById(1L);
        User user=users.get();
        user.addProduct(product);
        userService.saveUser(user);
        productService.saveProduct(product);
        model.addAttribute("product", product);

        return "redirect:/product/getall";
    }
}
