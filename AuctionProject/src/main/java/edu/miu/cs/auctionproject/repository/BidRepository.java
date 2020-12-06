package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}