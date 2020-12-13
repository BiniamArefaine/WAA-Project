package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.BidHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidHistoryRepository extends JpaRepository <BidHistory,Long>{

   public List<BidHistory> findAllBidHistoryByProductId(long id);

}
