package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.DepositPayment;

import java.util.List;
import java.util.Optional;

public interface DepositPaymentService {
    void savePayments(DepositPayment depositPayment);
    void deletePaymentById(Long id);
    Optional<DepositPayment> getPaymentById(Long id);
    List<DepositPayment> getAllDeposits();
    DepositPayment checkDeposit(long productId, Long userId);
    List<DepositPayment> getAllExceptWinner(Long userId);
    DepositPayment findDepositByProductId(Long productId);

    List<DepositPayment> findAllDepositsBypId(Long pId);

    DepositPayment findDepositByUserIdAndPId(Long id, long productIds);
}
