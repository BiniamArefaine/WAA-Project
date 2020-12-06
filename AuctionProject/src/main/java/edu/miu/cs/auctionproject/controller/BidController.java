package edu.miu.cs.auctionproject.controller;


import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.service.BidService;
import edu.miu.cs.auctionproject.service.impl.BidServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bids")
public class BidController {

    @Autowired
    BidService bidService;


    @GetMapping("/")
    private String getAllBids(){
        List<Bid> allBids = bidService.getAllBids();
        System.out.println(allBids + "---------------");
        return "error";
    }

    @PostMapping("/save_bid")
    private void saveBid(Bid bid){
        bidService.saveBid(bid);
    }
}
