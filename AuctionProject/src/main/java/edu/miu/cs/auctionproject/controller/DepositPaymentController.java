package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.*;
import edu.miu.cs.auctionproject.dynamicscheduling.MyJob;
import edu.miu.cs.auctionproject.dynamicscheduling.MyJobThreeDays;
import edu.miu.cs.auctionproject.javaMailApi.SendEmailClass;
import edu.miu.cs.auctionproject.service.BidHistoryService;
import edu.miu.cs.auctionproject.service.DepositPaymentService;
import edu.miu.cs.auctionproject.service.ProductService;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/payments")
@SessionAttributes({"prod"})
public class DepositPaymentController {

    @Autowired
    DepositPaymentService depositPaymentService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    MyJob myJob;

    @Autowired
    MyJobThreeDays myJobThreeDays;
    @Autowired
    private BidHistoryService bidHistoryService;

    @GetMapping("/getall_payments")
    private String getAllDeposits(){
        List<DepositPayment> allDeposits = depositPaymentService.getAllDeposits();
        System.out.println(allDeposits + "---------------");
        return "Home";
    }

    @PostMapping("/save_payments")
    private void savePayments(DepositPayment depositPayment){
        depositPaymentService.savePayments(depositPayment);
    }


    @GetMapping("/get_paymentById")
    private String getCategoriesById(Long id){
        Optional<DepositPayment> depositPayment = depositPaymentService.getPaymentById(id);
        System.out.println(depositPayment + "---------------");
        return "Home";
    }
    @DeleteMapping("/delete_payment")
    private void deleteCategory(Long id){
        depositPaymentService.deletePaymentById(id);
    }

    @GetMapping("/return_all_payments")
    private String returnAllPayments(User winner, Bid bid, List<Long> users, Long pId) {
        System.out.println("----------inside return all payment in DepositController");
        System.out.println(winner.getId() +""+ bid.getId() +""+ users.size()+""+pId);
        List<Long> listIds = users;
        List<DepositPayment> allDeposits = depositPaymentService.findAllDepositsBypId(pId);
        System.out.println(allDeposits.size());

        for (DepositPayment depositPayment: allDeposits){
            System.out.println("----------inside depost");

            if(depositPayment.getUser().getId() != winner.getId()) {
                System.out.println(depositPayment.getDeposit() + " dollars is returned to " + depositPayment.getUser().getFirstName()
                        + " " + depositPayment.getUser().getLastName());
//                SendEmailClass.sendMailTo(depositPayment.getUser().getEmail());

                depositPayment.setDeposit(0.0);
                depositPaymentService.savePayments(depositPayment);
            }
        }
        System.out.println("----------inside END scheludetime in paymentController");

        return "winner";
    }


    @PostMapping(value = {"/addpayment"})
    public String addDepositPayment(@Validated @ModelAttribute("depositPayment") DepositPayment depositPayment,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "payment/paymentfinal";
        }
        Product product=(Product) model.getAttribute("prod");
        product.setPaidInFull(true);
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        depositPayment.setProduct(product);
        depositPayment.setUser(user);
        depositPayment.setPaymentDate(LocalDate.now());
        productService.saveProduct(product);
        depositPaymentService.savePayments(depositPayment);
//        LocalDate localDateToBeShipped = LocalDate.now();
        System.out.println("-----Before triggered method-------------");
        triggeredMethod(product.getId());
        System.out.println("-----after triggered method-------------");

//        model.addAttribute("localDateToBeShipped", localDateToBeShipped);
        //added

        model.addAttribute("usercomf",user);
        model.addAttribute("depositpayment",depositPayment);
        return "payment/paymentconfirmation";
    }

    //scheduler for three days
    public void triggeredMethod(Long prodId){
        myJobThreeDays.timeThreeHashMap(prodId);

    }

    public void returnPaymentToCustomer(long productId){

        System.out.println("--------- if product is not null");
        if(productId != 0){
            System.out.println("--------- if product2 is not null");

            System.out.println("-----inside returned payment1 "+ productId);
            BidHistory bidHistory=bidHistoryService.printInvoiceByProductId(productId);
            System.out.println("-----inside returned payment2 "+ bidHistory.toString());

            User user=bidHistory.getUser();
            System.out.println("-----inside returned payment3 "+ productId);

            DepositPayment dp = depositPaymentService.checkDeposit(productId, user.getId());
            System.out.println("-----inside returned payment4 "+ productId);

            User userReturned = dp.getUser();
            //return payment back
            System.out.println("Full payment has been returned to: " + userReturned.getFirstName() + " "
                    + userReturned.getLastName()+ " an amount of " + dp.getFinalPayment());
            dp.setFinalPayment(0.0);
        }
    }

    public void returnPaymentToSeller(long pId){
        System.out.println("====== essey" + pId);
        if(pId != 0){
            System.out.println("prodct ID oro thrity days" + pId);
            User seller = userService.findUserByProductId(pId);
            System.out.println("-----inside returned payment1 "+ pId);
            BidHistory bidHistory=bidHistoryService.printInvoiceByProductId(pId);
            System.out.println("-----inside returned payment2 "+ bidHistory.toString());

            User user=bidHistory.getUser();
            System.out.println("-----inside returned payment3 "+ pId);

            DepositPayment dp = depositPaymentService.checkDeposit(pId, user.getId());
            System.out.println("-----inside returned payment4 "+ pId);

            User userReturned = dp.getUser();

            //return payment back
            System.out.println("Full payment has been returned to: " + seller.getFirstName() + " "
                    + seller.getLastName()+ " an amount of " + dp.getFinalPayment());
            dp.setFinalPayment(0.0);
        }
    }

    public void returnAlPayments(User winner, Bid bid, List<Long> users, Long pId){
        returnAllPayments(winner,bid,users, pId);
    }



}
