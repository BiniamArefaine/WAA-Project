package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.BidHistory;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BidHistoryService {



    List<BidHistory> findAllBidHistoryByProductId(long productId);

    BidHistory printInvoiceByProductId(long pId);

    void createBidHistory(Optional<Product> product, User user,double bidprice);
}
