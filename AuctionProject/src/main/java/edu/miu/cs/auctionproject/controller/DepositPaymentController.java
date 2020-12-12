package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Address;
import edu.miu.cs.auctionproject.domain.DepositPayment;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.service.DepositPaymentService;
import edu.miu.cs.auctionproject.service.ProductService;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private String returnAllPayments(Model model){
        System.out.println("----------inside return all payment in DepositController");
        //fake userId
        //not to be deleted
//        User user = (User)model.getAttribute("winner");
//        Long userId = user.getId();
//        long userId = 1L;
        List<DepositPayment> allDeposits = depositPaymentService.getAllExceptWinner(1L);

        for (DepositPayment depositPayment: allDeposits){
            System.out.println(depositPayment.getDeposit() + " dollars is returned to " + depositPayment.getUser().getFirstName()
                    + " " + depositPayment.getUser().getLastName());
        }
//        model.addAttribute("address", new Address());
        System.out.println("----------inside END scheludetime in paymentController");

        return "winner";
    }
    @PostMapping(value = {"/addpayment"})
    public String addDepositPayment(@Validated @ModelAttribute("depositPayment") DepositPayment depositPayment,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "payment/depositpayment";
        }
        Product product=(Product) model.getAttribute("prod");
        product.setPaidInFull(true);
        Long userId=(Long.parseLong(httpSession.getAttribute("userId").toString()));
        User user=userService.findUserById(userId).get();
        depositPayment.setProduct(product);
        depositPayment.setUser(user);
        depositPayment.setPaymentDate(LocalDate.now());
        product.setPaymentDate(LocalDate.now());
        productService.saveProduct(product);
        depositPaymentService.savePayments(depositPayment);
        model.addAttribute("usercomf",user);
        model.addAttribute("depositpayment",depositPayment);
        return "payment/paymentconfirmation";
    }
}
