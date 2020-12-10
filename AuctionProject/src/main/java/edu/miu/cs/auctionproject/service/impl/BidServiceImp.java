package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.repository.BidRepository;
import edu.miu.cs.auctionproject.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImp implements BidService {

    @Autowired
    BidRepository bidRepository;

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public Bid getBidById(Long id) {
         if(bidRepository.findById(id).isPresent()){
            return bidRepository.findById(id).get();
        }else {
             return null;
         }
    }

    @Override
    public void save(Bid bid) {
         bidRepository.save(bid);
    }

    @Override
    public void deleteById(Long id) {
          bidRepository.deleteById(id);
    }

    @Override
    public Bid getBidByProductId(Long id) {
        return bidRepository.findByProductId(id);
    }
    @Override
    public Double getHighestPrice(Bid bid, Product product) {
        double max = 0;
        if(bid!=null) {
            for (Double d : bid.getUsers().values()) {
                if (d > max) {
                    max = d;
                }
            }
        }
        if(max==0){
            return product.getStartingPrice();
        }

        return max;
    }


}
