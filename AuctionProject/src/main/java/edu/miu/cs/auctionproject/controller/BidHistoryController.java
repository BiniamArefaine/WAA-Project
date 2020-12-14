package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.BidHistory;
import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.service.BidHistoryService;

import edu.miu.cs.auctionproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bidHistory")
public class BidHistoryController {
    @Autowired
    BidHistoryService bidHistoryService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getHistoryById/{productId}")
    public String findAllBidHistoryById(@PathVariable("productId") long productId, Model model){
       List<BidHistory> bidHistorys=bidHistoryService.findAllBidHistoryByProductId(productId);
            model.addAttribute("historycount", bidHistorys.size());
            model.addAttribute("bidHistorys",bidHistorys);
            model.addAttribute("historycount", bidHistorys.size());

        return "bidhistory/listofbidhistory";
    }
    @GetMapping("/getInvoiceById/{productId}")
    public String printInvoiceById(@PathVariable("productId") long productId, Model model){
        BidHistory bidHistory=bidHistoryService.printInvoiceByProductId(productId);
        model.addAttribute("bidHistory",bidHistory);

        return "bidhistory/printinvoice";
    }


}
