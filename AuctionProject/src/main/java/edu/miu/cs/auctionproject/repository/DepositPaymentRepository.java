package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.DepositPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositPaymentRepository extends JpaRepository<DepositPayment, Long> {
    @Query("select d from DepositPayment d where d.user.id=:userId and d.product.id=:productId")
    DepositPayment getDepositPaymentByProductAndUser(Long productId,Long userId );
    @Query("select d from DepositPayment d where d.user.id <> :userId")
    List<DepositPayment> getAllExceptWinner(Long userId);
    DepositPayment getDepositPaymentByProductId(Long id);
}
