package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.Product;

import java.util.List;

public interface BidService {

    List<Bid> getAllBids();
    Bid getBidById(Long id);
    void save(Bid bid);
    void deleteById(Long id);

    Bid getBidByProductId(Long id);


    Double getHighestPrice(Bid bid, Product product);
}
