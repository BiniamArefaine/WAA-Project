package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
