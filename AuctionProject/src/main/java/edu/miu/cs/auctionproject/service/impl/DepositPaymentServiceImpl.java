package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.DepositPayment;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.repository.DepositPaymentRepository;
import edu.miu.cs.auctionproject.service.DepositPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositPaymentServiceImpl implements DepositPaymentService {

    @Autowired
    DepositPaymentRepository depositPaymentRepository;

    @Override
    public void savePayments(DepositPayment depositPayment) {
        depositPaymentRepository.save(depositPayment);

    }

    @Override
    public void deletePaymentById(Long id) {
         depositPaymentRepository.deleteById(id);
    }

    @Override
    public Optional<DepositPayment> getPaymentById(Long id) {
        return depositPaymentRepository.findById(id);
    }

    @Override
    public List<DepositPayment> getAllDeposits() {
        return depositPaymentRepository.findAll();
    }

    @Override
    public DepositPayment checkDeposit(long productId, Long userId) {
        return depositPaymentRepository.getDepositPaymentByProductAndUser(productId,userId);
    }
//    @Override
//    public DepositPayment getPaymentByProductId(long productId, long usrId) {
//        return depositPaymentRepository.getDepositPaymentByProductId(productId, usrId);
//    }
    @Override
    public List<DepositPayment> getAllExceptWinner(Long userId) {
        return depositPaymentRepository.getAllExceptWinner(userId);
    }

//    @Override
//    public User getUserByProductIdPaidFull(Long productId) {
//        return depositPaymentRepository.findUserByProductId(productId);
//    }

    @Override
    public DepositPayment findDepositByProductId(Long productId) {
        return null;
    }

    @Override
    public List<DepositPayment> findAllDepositsBypId(Long pId) {
        return depositPaymentRepository.findAllByproductId(pId);
    }

    @Override
    public DepositPayment findDepositByUserIdAndPId(Long id, long productIds) {
        return depositPaymentRepository.findByUseIdAndPId(id,productIds);
    }
}
