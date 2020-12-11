package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.repository.BidRepository;
import edu.miu.cs.auctionproject.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public User getUserBidById(Long bidId) {
        double max = 0;
        User user = null;
       if(bidRepository.findById(bidId).isPresent()){
           System.out.println("-----inside hashmap-----");
           Bid bid = bidRepository.findById(bidId).get();
           System.out.println("-----inside hashmap 2-----");
           for (Double d : bid.getUsers().values()){
                  if(d > max){
                      max = d;
                  }
           }
          for(User u : bid.getUsers().keySet()){
              if(bid.getUsers().get(u).equals(max)){
                  user = u;
              };
          }

       };

        return user;
    }



    @Override
    public Bid getBidByProductId(Long id){
        return bidRepository.getBidByProductId(id);
    }




}
