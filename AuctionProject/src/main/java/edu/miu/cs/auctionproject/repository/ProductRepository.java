package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //modified biniam-dave
//    List<Product> findAllByStartingPriceOrderByStartingPriceAsc();
//    List<Product> findAllByStartingPriceOrderByStartingPriceDesc();
//    List<Product> findAllByStartingPriceOrderByUpLoadedDateDesc();

}

