package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.repository.BidRepository;
import edu.miu.cs.auctionproject.service.BidService;
import edu.miu.cs.auctionproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BidServiceImp implements BidService {

    @Autowired
    BidRepository bidRepository;

    @Autowired
    UserService userService;

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
               System.out.println("-----inside hashmap doubles-----" + d);

               if(d > max){
                      max = d;
                      System.out.println("-----inside hashmap 2-----" + max);

                  }
           }
          for(Long userId : bid.getUsers().keySet()){
              if(bid.getUsers().get(userId).equals(max)){
                  user = userService.findUserById(userId).get();
                  System.out.println("-----inside hashmap 2-----" + user.getFirstName());

              };
          }

       };

        return user;
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



    @Override
    public Bid getBidByProductId(Long id){
        return bidRepository.getBidByProductId(id);
    }

    @Override
    public List<Long> allNonWinningUsers(Long bidId) {
        double max = 0;
        User user = null;
        List<Long> list = new ArrayList<>();
        if(bidRepository.findById(bidId).isPresent()){
            System.out.println("-----inside hashmap-----");
            Bid bid = bidRepository.findById(bidId).get();
            System.out.println("-----inside hashmap 2-----");
            for (Double d : bid.getUsers().values()){
                if(d > max){
                    max = d;
                }
            }
            for(Long userId : bid.getUsers().keySet()){
                if(!bid.getUsers().get(userId).equals(max)){
                    list.add(userId);
//                    user = userService.findUserById(userId).get();
                };
            }

        };

        return list;
    }


}
