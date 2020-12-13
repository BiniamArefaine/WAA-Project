package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.FileUploadUtil;
import edu.miu.cs.auctionproject.domain.*;
import edu.miu.cs.auctionproject.dynamicscheduling.MyJob;
import edu.miu.cs.auctionproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/product"})
@SessionAttributes({"prod"})
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @Autowired
    UserService userService;

    @Autowired
    BidService bidservice;

    @Autowired
    HttpSession httpSession;

    @Autowired
    DepositPaymentService depositPaymentService;

    @Autowired
    MyJob myJob;

//--------------------------------customer-----------------------------
@RequestMapping(value={"/getallWonProduct"})
public ModelAndView productWon(ModelAndView modelAndView,Model model) {
    User user=null;
    Optional<User> users=userService.findUserById((Long.parseLong(httpSession.getAttribute("userId").toString())));

    if(users.isPresent()){
        user=users.get();
    }
    List<Product> products= productService.findWonProducts(user);
    modelAndView.addObject("products",products);
    modelAndView.addObject("searchString", "");
    modelAndView.addObject("productsCount", products.size());
    modelAndView.setViewName("wonproducts");
    return modelAndView;
}
    @RequestMapping(value={"/getallpaidproducts"})
    public ModelAndView productPaidProducts(ModelAndView modelAndView,Model model) {
        User user=null;
        Optional<User> users=userService.findUserById((Long.parseLong(httpSession.getAttribute("userId").toString())));
        if(users.isPresent()){
            user=users.get();
        }
        List<Product> products= productService.findAllPaidProducts(user);
        modelAndView.addObject("products",products);
        modelAndView.addObject("productsCount", products.size());
        modelAndView.setViewName("paidproducts");
        return modelAndView;
    }

    @GetMapping(value = {"/payment/{productId}"})
    public String payProduct(@PathVariable long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user1=userService.findUserById(userId).get();
        DepositPayment depositPayment=depositPaymentService.getPaymentByProductId(productId);
        depositPayment.setFinalPayment(product.get().getMaxBidPrice()-depositPayment.getDeposit());
        model.addAttribute("prod", product.get());
        model.addAttribute("user1",user1);
        model.addAttribute("depositPayment",depositPayment);
        return "payment/paymentfinal";
    }

    @GetMapping(value={"/getall","/getall/pageNo"})
    public ModelAndView listProducts(@RequestParam(defaultValue = "0") int pageNo,ModelAndView modelAndView) {
        Page<Product> products=productService.findAllProducts(pageNo);
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user1=userService.findUserById(userId).get();
        List<Product>userProducts=user1.getProduct();
        List<Category>categories=categoryService.getAllCategories();
        modelAndView.addObject("products",products);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("userproducts", userProducts);
        modelAndView.addObject("productsCount", products.getTotalElements());
        modelAndView.addObject("categories",categories);
        modelAndView.addObject("currentPageNo",pageNo);
        modelAndView.setViewName("listproduct");
        return modelAndView;
    }

    @RequestMapping(value={"/getallByCategoryId/{categoryId}"})
    public ModelAndView listProductByCategoryId(@PathVariable long categoryId,ModelAndView modelAndView) {
        List<Product> products=productService.findAllProductsByCategory(categoryId);
        List<Category>categories=categoryService.getAllCategories();
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user1=userService.findUserById(userId).get();
        List<Product>userProducts=user1.getProduct();
        modelAndView.addObject("userproducts", userProducts);
        modelAndView.addObject("products",products);
        modelAndView.addObject("productsCount", products.size());
        modelAndView.addObject("categories",categories);
        modelAndView.setViewName("productListByFilter");
        return modelAndView;
    }
    @RequestMapping(value={"/getallbyuploadeddate/"})
    public ModelAndView listProductByUploadedDate(ModelAndView modelAndView) {
        List<Product>products=productService.findAllByUploadedDate();
        List<Category>categories=categoryService.getAllCategories();
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user1=userService.findUserById(userId).get();
        List<Product>userProducts=user1.getProduct();
        modelAndView.addObject("userproducts", userProducts);
        modelAndView.addObject("products",products);
        modelAndView.addObject("productsCount", products.size());
        modelAndView.addObject("categories",categories);
        modelAndView.setViewName("productListByFilter");
        return modelAndView;
    }
    @GetMapping(value = {"/search"})
    public ModelAndView searchAuctionProducts(@RequestParam(defaultValue = "0")int pageNo,@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Category>categories=categoryService.getAllCategories();
        Page<Product> products = productService.searchProduct(pageNo,searchString);
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user1=userService.findUserById(userId).get();
        List<Product>userProducts=user1.getProduct();
        modelAndView.addObject("userproducts", userProducts);
        modelAndView.addObject("products", products);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("categories",categories);
        modelAndView.addObject("productsCount", products.getTotalElements());
        modelAndView.addObject("currentPageNo",pageNo);
        modelAndView.setViewName("listproduct");
        return modelAndView;
    }
    @GetMapping(value = {"/details/{productId}"})
    public String productDetails(@PathVariable long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user1=userService.findUserById(userId).get();
        List<Product>userProducts=user1.getProduct();

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("userproducts", userProducts);
            return "/productdetails";
        }
        return "listproduct";
    }

    @GetMapping(value = {"/received/{productId}"})
    public String orderReceived(@PathVariable long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);
        if (product.isPresent()) {
            product.get().setReceived(true);
            productService.saveProduct(product.get());
            return "redirect:/product/getallpaidproducts";
        }
        return "redirect:/product/getallpaidproducts";
    }


//----------------------------------------seller--------------------------------------------

    @RequestMapping(value={"/seller/getall"})
    public ModelAndView listProducts(ModelAndView modelAndView,Model model) {
        User user=null;
        Optional<User> users=userService.findUserById((Long.parseLong(httpSession.getAttribute("userId").toString())));

        if(users.isPresent()){
            user=users.get();
        }
        List<Product> products=user.getProduct();
        modelAndView.addObject("products",products);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("productsCount", products.size());
        modelAndView.setViewName("secured/seller/userproducts");
        return modelAndView;
    }

    @RequestMapping(value={"/seller/getallsold"})
    public ModelAndView listProductsSold(ModelAndView modelAndView,Model model) {
        User user=null;
        Optional<User> users=userService.findUserById((Long.parseLong(httpSession.getAttribute("userId").toString())));

        if(users.isPresent()){
            user=users.get();
        }
        List<Product> products=productService.getProductSold(user);
        modelAndView.addObject("products",products);
        modelAndView.addObject("productsCount", products.size());
        modelAndView.addObject("BidHasStarted","");
        modelAndView.setViewName("secured/seller/soldproducts");
        return modelAndView;
    }


    @GetMapping(value = {"/seller/ship/{productId}"})
    public String shipProduct(@PathVariable long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);
        if (product.isPresent()) {
            DepositPayment depositPayment=depositPaymentService.getPaymentByProductId(productId);
            User user=depositPayment.getUser();
            Optional<User> users=userService.findUserById((Long.parseLong(
                    httpSession.getAttribute("userId").toString())));
            model.addAttribute("productShippedId",productId);
            model.addAttribute("customer",user);
            model.addAttribute("seller",users.get());
            return "secured/seller/shipment";
        }
        return "redirect:/product/seller/getallsold";
    }

    @GetMapping(value = {"/seller/shipped/{productId}"})
    public String saveShipment(@PathVariable long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);
        if (product.isPresent()) {
            product.get().setShipped(true);
            productService.saveProduct(product.get());
            return "redirect:/product/seller/getallsold";
        }
        return "redirect:/product/seller/getallsold";
    }



    @RequestMapping(value = {"/seller/new" })
    public String inputProduct(@ModelAttribute("product") Product product,Model model) {
        List<Category>categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("depomessage","");
        return "/secured/seller/addproduct";
//        return "redirect:/bids/duedate_done";
    }


    @PostMapping(value = "/seller/add")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
                              @RequestParam("files") MultipartFile[] files,
                              Model model) throws IOException {
        Boolean depositpayment=productService.checkSetDepsoit(product);
        double calculateDeposit=productService.calculateDepositPayment(product);
        if (bindingResult.hasErrors()|| depositpayment) {
            List<Category>categories = categoryService.getAllCategories();
            product.setDepositpayment(calculateDeposit);
            model.addAttribute("depomessage","Deposit must be greater than 10% of starting price");
            model.addAttribute("categories", categories);
            return "/secured/seller/addproduct";
        }


        User user=null;
        Optional<User> users=userService.findUserById((Long.parseLong(
                httpSession.getAttribute("userId").toString())));
        if(users.isPresent()){
            user=users.get();
        }
        String fileName="";
        for(MultipartFile file:files){
            fileName=StringUtils.cleanPath(file.getOriginalFilename());
            product.addPhoto("/images/product-photos/" + user.getId()+ "/" +fileName);
            String uploadDir = "src/main/resources/static/images/product-photos/" +user.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }
        List<Product> products=user.getProduct();
        product.setMaxBidPrice(product.getStartingPrice());
        products.add(product);
        userService.saveUser(user);
        model.addAttribute("product", product);
        return "redirect:/product/seller/getall";
    }



    @PostMapping(value = {"/seller/edit"})
    public String updateProduct(@Validated @ModelAttribute("product") Product product,
                                BindingResult bindingResult,@RequestParam("files") MultipartFile[] files,
                                Model model) throws IOException {
        Boolean depositpayment=productService.checkSetDepsoit(product);
        double calculateDeposit=productService.calculateDepositPayment(product);
        if (bindingResult.hasErrors()|| depositpayment) {
            product.setDepositpayment(calculateDeposit);
            model.addAttribute("depomessage","Deposit must be greater than 10% of starting price");
            List<Category>categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "secured/seller/productEdit";
        }
        User user=null;
        Optional<User> users=userService.findUserById((Long.parseLong(
                httpSession.getAttribute("userId").toString())));
        if(users.isPresent()){
            user=users.get();
        }
        String fileName="";
        for(MultipartFile file:files){
            fileName=StringUtils.cleanPath(file.getOriginalFilename());
            product.addPhoto("/images/product-photos/" + user.getId()+ "/" +fileName);
            String uploadDir = "src/main/resources/static/images/product-photos/" +user.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        }
        product.setMaxBidPrice(product.getStartingPrice());
        productService.saveProduct(product);
        return "redirect:/product/seller/getall";
    }



    @GetMapping(value = {"/seller/edit/{productId}"})
    public String editProduct(@PathVariable long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);
        if(product.isPresent()){
            if(product.get().getBidcount()>0){
                return "redirect:/product/seller/getall";
            }
        }
        if (product.isPresent()) {
            model.addAttribute("depomessage","");
            model.addAttribute("product", product.get());
            model.addAttribute("categories",categoryService.getAllCategories());
            return "secured/seller/productEdit";
        }
        return "secured/seller/userproducts";
    }



    @GetMapping(value = {"/seller/delete/{productId}"})
    public String deleteStudent(@PathVariable Long productId, Model model) {
        Optional<Product> products=productService.findProductById(productId);
        if(products.isPresent()){
            if(products.get().getBidcount()>0){
                model.addAttribute("BidHasStarted","BidHasStarted");
              return "redirect:/product/seller/getall";
            }
        }
        productService.deleteProduct(productId);
        return "redirect:/product/seller/getall";
    }


    @GetMapping("/ispaid")
    public String isPaidBeforePaymentDay(Long productId){
        //fake id
        long productIds = 1L;
        if(productService.findProductById(productId).isPresent()){
           Product productToBeShipped = productService.findProductById(productIds).get();
           if(!productToBeShipped.isPaidInFull()
                   && !LocalDate.now().isAfter(productToBeShipped.getPaymentDate())){
               return "redirect:/product/shipped";
           }
        };

        System.out.println("Payment is not done on due date or is not paid in full!!");
        //to be completed
           return "";
    }

    //shipment
    @GetMapping("/shipped")
    public String shipmentProduct(){
      //fake Id
        Long userId = 1L;
        if(userService.findUserById(1L).isPresent()){
            Address userAddress = userService.findUserById(1L).get().getAddress();
            System.out.println("The product has been shipped to" + userAddress.toString());
        }

        return "congratulationsShipped";
    }




}