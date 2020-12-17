package edu.miu.cs.auctionproject.controller;


import edu.miu.cs.auctionproject.domain.*;
import edu.miu.cs.auctionproject.dynamicscheduling.MyJob;
import edu.miu.cs.auctionproject.dynamicscheduling.MyJobPaymentDueDate;
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
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    DepositPaymentController depositPaymentController;

    @Autowired
    MyJobPaymentDueDate myJobPaymentDueDate;


    //---------------------------making bid---------------------------------------
    @PostMapping(value = {"/addDeposit"})
    public String addDepositPayment(@Validated @ModelAttribute("depositPayment") DepositPayment depositPayment,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "payment/depositpayment";
        }
        Long productId = (Long.parseLong(httpSession.getAttribute("productId").toString()));
        Long userId = (Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user = userService.findUserById(userId).get();
        Optional<Product> product1 = productService.findProductById(productId);
        depositPayment.setPaymentDate(LocalDate.now());
        depositPayment.setUser(user);
        depositPayment.setProduct(product1.get());
        depositPaymentService.savePayments(depositPayment);
        return "redirect:/bids/addBid";
    }

    @GetMapping(value = {"add/{productId}"})
    public String addBid(@PathVariable long productId, Model model) {
        Long userId = (Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user = userService.findUserById(userId).get();
        httpSession.setAttribute("productId", productId);
        Optional<Product> product = productService.findProductById(productId);
        DepositPayment depositPayment = depositPaymentService.checkDeposit(productId, userId);
        if (user.isVerification()) {
            if (user.getProduct().contains(product.get())) {
                return "redirect:/product/getall";
            }
            if (depositPayment == null) {
                DepositPayment depositPayment1 = new DepositPayment();
                depositPayment1.setDeposit(product.get().getDepositpayment());
                model.addAttribute("bidproduct", product.get());
                model.addAttribute("depositPayment", depositPayment1);
                return "payment/depositpayment";
            }

        } else {
            return "notVerified";
        }

        return "redirect:/bids/addBid";
    }

    @RequestMapping(value = {"/addBid"})
    public String inputBid(Model model) {
        Long productId = (Long.parseLong(httpSession.getAttribute("productId").toString()));
        Optional<Product> product = productService.findProductById(productId);
        Bid bid = bidService.getBidByProductId(product.get().getId());
        Double bidPrice = bidService.getHighestPrice(bid, product.get());
        model.addAttribute("maxbidprice", product.get().getMaxBidPrice());
        model.addAttribute("enterLarger", "");
        model.addAttribute("bidPrice", bidPrice);

        return "addBidPrice";
    }

    @RequestMapping(value = {"/saveBid"})
    public String addBid(@ModelAttribute("bidPrice") Double bidPrice,
                         BindingResult bindingResult,
                         Model model) {

        Long productId = (Long.parseLong(httpSession.getAttribute("productId").toString()));
        Optional<Product> product = productService.findProductById(productId);
        long productIds = product.get().getId();
        //MyJob class scheduler
        System.out.println("dave----inside save Bid" + productIds);
        LocalDate localDate = product.get().getDueDate();
        LocalDate paymentDate = product.get().getPaymentDueDate();

        myJob.timeHashMap(productIds, localDate.atStartOfDay());

        Long userId = (Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user = userService.findUserById(userId).get();
        Bid bid = bidService.getBidByProductId(product.get().getId());
        if (bidPrice <= product.get().getMaxBidPrice()) {
            model.addAttribute("maxbidprice", product.get().getMaxBidPrice());
            model.addAttribute("enterLarger", "Please Enter More than the value");
            return "addBidPrice";
        }
        if (bid == null) {
            bid = new Bid();
            HashMap<Long, Double> userHashMap = new HashMap<Long, Double>();
            userHashMap.put(user.getId(), bidPrice);
            bid.setUsers(userHashMap);
        } else {
            if (bid.getUsers().containsKey(user.getId())) {
                bid.getUsers().replace(user.getId(), bidPrice);
            }
        }
        bid.setProduct(product.get());
        product.get().setBidcount(product.get().getBidcount() + 1);
        product.get().setMaxBidPrice(bidPrice);
        bidHistoryService.createBidHistory(product, user, bidPrice);
        bidService.save(bid);
        return "redirect:/product/getall";
    }


    @GetMapping(value = "/before_duedate")
    public String scheduleTime(Long productId) {

        System.out.println("----------inside scheludetime in BidController " + productId);
        Bid bid = bidService.getBidByProductId(productId);
        System.out.println("------ bid passed " + bid.toString());
        //fake userId
        Long userId = bidService.getUserBidById(bid.getId()).getId();
        List<Long> listUserIds = bidService.allNonWinningUsers(userId);

        System.out.println("------ userId passed");
        User winner = null;
        if (userService.findUserById(userId).isPresent()) {
            winner = userService.findUserById(userId).get();
            Optional<Product> prd = productService.findProductById(productId);
            Product product = prd.get();
            System.out.println(product.toString());
            product.setSold(true);
            System.out.println(productId +" ");
            System.out.println(product.getPaymentDueDate().atStartOfDay() +" ");

            myJobPaymentDueDate.timePaymentHashMap(productId,product.getPaymentDueDate().atStartOfDay());
            productService.saveProduct(product);
            List<Product> products = winner.getWonProducts().stream().filter(product1 ->
                    product1.getId() == productId).collect(Collectors.toList());
            if (products.isEmpty()) {
                winner.getWonProducts().add(product);
                userService.saveUser(winner);
            }

        }
        ;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("winner", winner);
        modelAndView.addObject("bid", bid);
        modelAndView.addObject("listOfNonWinning", listUserIds);

        System.out.println("----------inside END scheludetime in BidController");
        returnToAllPayments(winner,bid,listUserIds,productId);
        return "redirect:/payments/return_all_payments";

    }

    public void scheduler(long productId) {
        if(productId !=0 ) {
            long prodId = productId;
            scheduleTime(productId);
        }
    }

    public void returnToAllPayments(User winner, Bid bid, List<Long> users,Long pId){
        if(pId!=null) {
            depositPaymentController.returnAlPayments(winner, bid, users, pId);
        }
    }


}
