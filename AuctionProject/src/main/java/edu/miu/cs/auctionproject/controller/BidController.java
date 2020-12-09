package edu.miu.cs.auctionproject.controller;


import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.DepositPayment;
import edu.miu.cs.auctionproject.domain.Product;
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


    @GetMapping("/getall_bids")
    private String getAllBids(){
        List<Bid> allBids = bidService.getAllBids();
        System.out.println(allBids + "---------------");
        return "Home";
    }

    @PostMapping("/save_bid")
    private void saveBid(Bid bid){
        bidService.save(bid);
    }

    @GetMapping("/get_bidbyid/{id}")
    private Bid getBidsById(@PathVariable("id") Long id){
        System.out.println("------dava----");
        Bid bid = bidService.getBidById(id);
        System.out.println(bid + "---------------");
        return bid;
    }
    @DeleteMapping("/delete_bid")
    private void deleteBid(Long id){
        bidService.deleteById(id);
    }


    //---------------------------making bid---------------------------------------
    @PostMapping(value = {"/addDeposit"})
    public String addDepositPayment(@Validated @ModelAttribute("depositPayment") DepositPayment depositPayment,
                                    BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            return "depositpayment";
        }
        System.out.println("wegahta"+depositPayment.getProduct());
        depositPaymentService.savePayments(depositPayment);
        return "redirect:/bids/addBid";
    }

    @GetMapping(value = {"add/{productId}"})
    public String addBid(@PathVariable long productId, Model model) {
        Long userId=(Long.parseLong(servletContext.getAttribute("userId").toString()));
        System.out.println("========================="+productId);
        DepositPayment depositPayment=depositPaymentService.checkBid(productId,userId);

        if(depositPayment==null){
            DepositPayment depositPayment1=new DepositPayment();
            Optional<Product> product=productService.findProductById(productId);
            depositPayment1.setDeposit(product.get().getStartingPrice()*0.01);
            depositPayment1.setProduct(product.get());
            depositPayment1.setUser(userService.findUserById(userId).get());
            model.addAttribute("product",product.get());
            model.addAttribute("depositPayment",depositPayment1);
            return "depositpayment";
        }
        return "null";
    }
    @RequestMapping(value = {"/addBid" })
    public String inputBid(Model model) {
        Long product= (Long) model.getAttribute("productId");
        System.out.println(product);
//        Double deposit=product.getStartingPrice()*0.01;
//        depositPayment.setDeposit(deposit);
        int bidPrice=0;
        model.addAttribute("bidPrice", bidPrice);
        return "addBidPrice";
    }

}
