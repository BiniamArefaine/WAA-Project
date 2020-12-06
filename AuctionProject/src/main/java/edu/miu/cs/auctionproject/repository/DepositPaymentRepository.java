package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.DepositPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositPaymentRepository extends JpaRepository<DepositPayment, Long> {
}
