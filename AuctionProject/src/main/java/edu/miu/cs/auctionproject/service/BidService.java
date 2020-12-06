package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Bid;

import java.util.List;
import java.util.Optional;

public interface BidService {

    List<Bid> getAllBids();
    Optional<Bid> getBidById(Long id);
    void saveBid(Bid bid);
    void deleteBid(Long id);

}
