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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Controller
@SessionAttributes({"bidproduct"})
@RequestMapping("/bids")
public class BidController {

    @Autowired
    BidService bidService;


    @Autowired
    HttpSession httpSession;


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
        if (bindingResult.hasErrors()) {
            return "depositpayment";
        }
        Long productId= (Long.parseLong(httpSession.getAttribute("productId").toString()));
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        Optional<Product> product1=productService.findProductById(productId);
        depositPayment.setUser(user);
        depositPayment.setProduct(product1.get());
        depositPaymentService.savePayments(depositPayment);
        return "redirect:/bids/addBid";
    }

    @GetMapping(value = {"add/{productId}"})
    public String addBid(@PathVariable long productId, Model model) {
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        httpSession.setAttribute("productId",productId);
        Optional<Product> product = productService.findProductById(productId);
        DepositPayment depositPayment=depositPaymentService.checkDeposit(productId,userId);
        if(user.isVerification()) {
//            if(user.getProduct().contains(product.get())){
//            return "redirect:/product/getall";
//            }
            if (depositPayment == null) {
                DepositPayment depositPayment1 = new DepositPayment();
                depositPayment1.setDeposit(product.get().getStartingPrice() * 0.01);
                model.addAttribute("bidproduct", product.get());
//                model.addAttribute("user", userService.findUserById(userId).get());
                model.addAttribute("depositPayment", depositPayment1);
                return "payment/depositpayment";
            }else {
//                model.addAttribute("bidproduct", product.get());
//                model.addAttribute("user", userService.findUserById(userId).get());
            }

        }
        if(!user.isVerification()){
            return "redirect:/product/getall";
        }
        return "forward:/bids/addBid";
    }
    @RequestMapping(value = {"/addBid" })
    public String inputBid(Model model) {
        Product product= (Product) model.getAttribute("bidproduct");
        Bid bid=bidService.getBidByProductId(product.getId());
        Double bidPrice=bidService.getHighestPrice(bid,product);
        model.addAttribute("bidPrice", bidPrice);

        return "addBidPrice";
    }
    @RequestMapping(value = {"/saveBid"})
    public String addBid(@ModelAttribute("bidPrice") Double bidPrice,
                                    BindingResult bindingResult,
                                    Model model) {

        Product product= (Product) model.getAttribute("bidproduct");
        Product product1=productService.findProductById(product.getId()).get();
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        Bid bid=bidService.getBidByProductId(product.getId());

        if(bid==null){
            bid=new Bid();
            HashMap<Long,Double> userHashMap=new HashMap<Long, Double>();
            userHashMap.put(user.getId(),bidPrice);
            bid.setUsers(userHashMap);
        }
        else{

            if(bid.getUsers().containsKey(user.getId())){
                bid.getUsers().replace(user.getId(), bidPrice);
            }
        }
        bid.setProduct(product1);
        product1.setBidcount(product1.getBidcount()+1);
        product1.setMaxBidPrice(bidPrice);
//        productService.saveProduct(product);

        bidService.save(bid);
        return "redirect:/product/getall";
    }

    //for scheduling and bidding
//    @Scheduled(fixedRate = )
    @GetMapping(value = "/before_duedate")
    public String scheduleTime(Long productId, Model model){
        System.out.println("----------inside scheludetime in BidController");
       Bid bid = bidService.getBidByProductId(productId);
        System.out.println("------ bid passed");
        //fake userId
       Long userId = bidService.getUserBidById(1L).getId();
        System.out.println("------ userId passed");
        User winner = null;
        if(userService.findUserById(1L).isPresent()){
             winner = userService.findUserById(1L).get();
        };
        //get the hashMap and get the user
        //fake usr object
        System.out.println("-----after null");
        model.addAttribute("winner", winner);
        System.out.println("-----after winner");

        //fake bidId
        System.out.println("----------inside END scheludetime in BidController");
        return "forward:/payments/return_all_payments";
    }
}
