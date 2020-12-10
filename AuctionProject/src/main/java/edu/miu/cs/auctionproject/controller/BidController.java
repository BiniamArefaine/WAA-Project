package edu.miu.cs.auctionproject.controller;


import edu.miu.cs.auctionproject.domain.*;
import edu.miu.cs.auctionproject.service.BidService;

import edu.miu.cs.auctionproject.service.DepositPaymentService;
import edu.miu.cs.auctionproject.service.ProductService;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;
@Controller
@SessionAttributes({"product","user"})
@RequestMapping("/bids")
public class BidController {

    @Autowired
    BidService bidService;


    @Autowired
    ServletContext servletContext;


    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepositPaymentService depositPaymentService;

//
//    @GetMapping("/getall_bids")
//    private String getAllBids(){
//        List<Bid> allBids = bidService.getAllBids();
//        System.out.println(allBids + "---------------");
//        return "Home";
//    }

//    @PostMapping("/save_bid")
//    private void saveBid(Bid bid){
//        bidService.save(bid);
//    }

//    @GetMapping("/get_bidbyid/{id}")
//    private Bid getBidsById(@PathVariable("id") Long id){
//        System.out.println("------dava----");
//        Bid bid = bidService.getBidById(id);
//        System.out.println(bid + "---------------");
//        return bid;
//    }
//    @DeleteMapping("/delete_bid")
//    private void deleteBid(Long id){
//        bidService.deleteById(id);
//    }


    //---------------------------making bid---------------------------------------
    @PostMapping(value = {"/addDeposit"})
    public String addDepositPayment(@Validated @ModelAttribute("depositPayment") DepositPayment depositPayment,
                                    BindingResult bindingResult,
                               Model model) {
        System.out.println(model.getAttribute("product"));
        if (bindingResult.hasErrors()) {
            return "depositpayment";
        }
        depositPayment.setUser((User) model.getAttribute("user"));
        depositPayment.setProduct((Product) model.getAttribute("product"));
        depositPaymentService.savePayments(depositPayment);
        return "redirect:/bids/addBid";
    }

    @GetMapping(value = {"add/{productId}"})
    public String addBid(@PathVariable long productId, Model model) {
        Long userId=(Long.parseLong(servletContext.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        Optional<Product> product = productService.findProductById(productId);
        DepositPayment depositPayment=depositPaymentService.checkBid(productId,userId);
        if(user.isVerification()) {
            if(user.getProduct().contains(product.get())){
            return "redirect:/product/getall";
            }
            if (depositPayment == null) {
                DepositPayment depositPayment1 = new DepositPayment();
                depositPayment1.setDeposit(product.get().getStartingPrice() * 0.01);
                model.addAttribute("product", product.get());
                model.addAttribute("user", userService.findUserById(userId).get());
                model.addAttribute("depositPayment", depositPayment1);
                return "depositpayment";
            }

        }
        if(!user.isVerification()){
            return "redirect:/product/getall";
        }
        return "redirect:/bids/addBid";
    }
    @RequestMapping(value = {"/addBid" })
    public String inputBid(Model model) {
        Product product= (Product) model.getAttribute("product");
        Bid bid=bidService.getBidByProductId(product.getId());
        Double bidPrice=bidService.getHighestPrice(bid,product);
        System.out.println(bidPrice);
        model.addAttribute("bidPrice", bidPrice);
        return "addBidPrice";
    }
    @PostMapping(value = {"/saveBid"})
    public String addBid(@ModelAttribute("bidPrice") Double bidPrice,
                                    BindingResult bindingResult,
                                    Model model) {
        Product product= (Product) model.getAttribute("product");
        User user=(User) model.getAttribute("user");
        Bid bid=bidService.getBidByProductId(product.getId());
        if(bid==null){
            bid=new Bid();
        }
        if(bid.getUsers().containsKey(user)){
            bid.getUsers().replace(user,bidPrice);
        }else {
            bid.getUsers().put(user, bidPrice);
        }
        bid.setProduct((Product) model.getAttribute("product"));
        product.setBidcount(product.getBidcount()+1);
        product.setMaxBidPrice(bidPrice);
        productService.saveProduct(product);
        bidService.save(bid);
        return "forward:/product/getall";
    }


}
