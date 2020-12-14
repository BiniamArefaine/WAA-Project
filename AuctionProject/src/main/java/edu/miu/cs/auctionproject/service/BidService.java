package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;

import java.util.List;
import java.util.Optional;

public interface BidService {

    List<Bid> getAllBids();
    Bid getBidById(Long id);
    void save(Bid bid);
    void deleteById(Long id);
    User getUserBidById(Long id);
    Bid getBidByProductId(Long id);
    //added
    List<Long> allNonWinningUsers(Long usrId);

    Double getHighestPrice(Bid bid, Product product);
}
