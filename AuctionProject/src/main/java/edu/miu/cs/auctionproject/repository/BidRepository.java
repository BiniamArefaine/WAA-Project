package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
        Bid findByProductId(Long produtId);

//        @Query("select b from Bid b where b.product.id=: id")
        Bid getBidByProductId(Long id);
}
