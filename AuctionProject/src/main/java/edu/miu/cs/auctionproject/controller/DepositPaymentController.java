package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.DepositPayment;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.service.DepositPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/payments")
@SessionAttributes({"prod","user1"})
public class DepositPaymentController {

    @Autowired
    DepositPaymentService depositPaymentService;


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


    @PostMapping(value = {"/addpayment"})
    public String addDepositPayment(@Validated @ModelAttribute("depositPayment") DepositPayment depositPayment,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "payment/depositpayment";
        }
        Product product=(Product) model.getAttribute("prod");
        product.setSold(true);
        depositPayment.setUser((User) model.getAttribute("user1"));
        depositPayment.setProduct(product);
        depositPaymentService.savePayments(depositPayment);
        return "redirect:/product/getallWonProduct";
    }


}
