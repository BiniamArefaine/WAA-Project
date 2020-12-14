package edu.miu.cs.auctionproject.controller;


import edu.miu.cs.auctionproject.domain.*;
import edu.miu.cs.auctionproject.dynamicscheduling.MyJob;
import edu.miu.cs.auctionproject.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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
    BidHistoryService bidHistoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    MyJob myJob;

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
            return "payment/depositpayment";
        }
        Long productId= (Long.parseLong(httpSession.getAttribute("productId").toString()));
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        Optional<Product> product1=productService.findProductById(productId);
        depositPayment.setPaymentDate(LocalDate.now());
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
            if(user.getProduct().contains(product.get())){
            return "redirect:/product/getall";
            }
            if (depositPayment == null) {
                DepositPayment depositPayment1 = new DepositPayment();
                depositPayment1.setDeposit(product.get().getDepositpayment());
                model.addAttribute("bidproduct", product.get());
//                model.addAttribute("user", userService.findUserById(userId).get());
                model.addAttribute("depositPayment", depositPayment1);
                return "payment/depositpayment";
            }

        }
        else {
            return "notVerified";
        }

        return "redirect:/bids/addBid";
    }
    @RequestMapping(value = {"/addBid" })
    public String inputBid(Model model) {
        Long productId= (Long.parseLong(httpSession.getAttribute("productId").toString()));
        Optional<Product> product=productService.findProductById(productId);
        Bid bid=bidService.getBidByProductId(product.get().getId());
        Double bidPrice=bidService.getHighestPrice(bid,product.get());
        model.addAttribute("maxbidprice",product.get().getMaxBidPrice());
        model.addAttribute("enterLarger","");
        model.addAttribute("bidPrice", bidPrice);

        return "addBidPrice";
    }
    @RequestMapping(value = {"/saveBid"})
    public String addBid(@ModelAttribute("bidPrice") Double bidPrice,
                                    BindingResult bindingResult,
                                    Model model) {

        Long productId= (Long.parseLong(httpSession.getAttribute("productId").toString()));
        Optional<Product> product=productService.findProductById(productId);
        long productIds = product.get().getId();
        //MyJob class scheduler
        System.out.println("dave----inside save Bid" + productIds);
        LocalDate localDate = product.get().getDueDate();
        myJob.timeHashMap(productIds,localDate.atStartOfDay());

        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        Bid bid=bidService.getBidByProductId(product.get().getId());
        if(bidPrice<=product.get().getMaxBidPrice()){
            model.addAttribute("maxbidprice",product.get().getMaxBidPrice());
            model.addAttribute("enterLarger","Please Enter More than the value");
                return "addBidPrice";
        }
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
        bid.setProduct(product.get());
        product.get().setBidcount(product.get().getBidcount()+1);
        product.get().setMaxBidPrice(bidPrice);
//        productService.saveProduct(product);
        bidHistoryService.createBidHistory(product,user,bidPrice);
        bidService.save(bid);
        return "redirect:/product/getall";
    }

    //for scheduling and bidding
//    @Scheduled(fixedRate = )
    @GetMapping(value = "/before_duedate")
    public String scheduleTime(Long productId){
        System.out.println("----------inside scheludetime in BidController");
       Bid bid = bidService.getBidByProductId(productId);
        System.out.println("------ bid passed");
        //fake userId
       Long userId = bidService.getUserBidById(1L).getId();
        System.out.println("------ userId passed");
        User winner = null;
        if(userService.findUserById(1L).isPresent()){
             winner = userService.findUserById(1L).get();
             Optional<Product> prd = productService.findProductById(1L);
             prd.get().setSold(true);
             productService.saveProduct(prd.get());

        };
        //get the hashMap and get the user
        //fake usr object
        System.out.println("-----after null");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("winner", winner);
        System.out.println("-----after winner");

        //fake bidId
        System.out.println("----------inside END scheludetime in BidController");
        return "forward:/payments/return_all_payments";
    }


    public void scheduler(long productId) {
        scheduleTime(productId);
    }
}
