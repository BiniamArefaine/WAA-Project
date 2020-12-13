package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.BidHistory;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.repository.BidHistoryRepository;
import edu.miu.cs.auctionproject.service.BidHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BidHistoryServiceImp implements BidHistoryService {
    @Autowired
    BidHistoryRepository bidHistoryRepository;

    @Override
    public List<BidHistory> findAllBidHistoryByProductId(long productId) {
        return bidHistoryRepository.findAllBidHistoryByProductId(productId);
    }

    @Override
    public BidHistory printInvoiceByProductId(long productId) {
        BidHistory bidHistory=null;
        double max=0;
        List<BidHistory> allHistory= bidHistoryRepository.findAllBidHistoryByProductId(productId);
        for(BidHistory bid:allHistory){
            if(bid.getBidAmount()>max){
                max=bid.getBidAmount();
                bidHistory=bid;
            }

        }
        return bidHistory;
    }

    @Override
    public void createBidHistory(Optional<Product> product, User user,double bidPrice) {
       BidHistory bidHistory=new BidHistory();
       bidHistory.setBidAmount(bidPrice);
       bidHistory.setDateOfBid(LocalDate.now());
        if(product.isPresent()){
            bidHistory.setProduct(product.get());
            bidHistory.setUser(user);
        }
        bidHistoryRepository.save(bidHistory);
    }


}

