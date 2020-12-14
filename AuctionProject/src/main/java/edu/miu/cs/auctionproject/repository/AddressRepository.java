package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findByUserId(long userId);
}
