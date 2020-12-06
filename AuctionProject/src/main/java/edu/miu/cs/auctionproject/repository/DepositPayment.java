package edu.miu.cs.auctionproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositPayment extends JpaRepository<DepositPayment, Long> {
}
