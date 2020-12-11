package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Address;
import edu.miu.cs.auctionproject.domain.DepositPayment;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.service.DepositPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/payments")
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
}
