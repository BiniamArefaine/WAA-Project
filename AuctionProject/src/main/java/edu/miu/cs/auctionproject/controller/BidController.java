package edu.miu.cs.auctionproject.controller;


import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.service.BidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    BidService bidService;


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

}
