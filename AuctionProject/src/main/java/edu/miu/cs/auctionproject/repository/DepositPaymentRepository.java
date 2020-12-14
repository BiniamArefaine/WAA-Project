package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.DepositPayment;
import edu.miu.cs.auctionproject.domain.User;
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

    @Query("select d from DepositPayment d where d.product.id=: id and d.user.id=: usrId")
    DepositPayment getDepositPaymentByProductId(long id, long usrId);

//    @Query("select d.user from DepositPayment d where d.product.id=: pId and d.finalPayment>0")
//    User findUserByProductId(Long pId);

    @Query("select d from DepositPayment d where d.product.id=: pId")
    DepositPayment findDepositPaymentByProductId(Long pId);

    @Query("select d from DepositPayment d where d.product.id= :pId")
    List<DepositPayment> findAllByproductId(Long pId);

    @Query("select d from DepositPayment d where d.user.id= :userId and d.product.id= :pId")
    DepositPayment findByUseIdAndPId(long userId, long pId);
}
