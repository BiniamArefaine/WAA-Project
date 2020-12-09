package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.repository.BidRepository;
import edu.miu.cs.auctionproject.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


}
