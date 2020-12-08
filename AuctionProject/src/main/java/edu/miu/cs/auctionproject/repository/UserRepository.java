package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<Product>findAllById(long id);
}
