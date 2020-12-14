package edu.miu.cs.auctionproject.repository;

import edu.miu.cs.auctionproject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
//    @Query(value = "SELECT p FROM  Product p WHERE p.productName like %:searchString%")

    @Query(value = "SELECT p FROM Product p WHERE p.release='Yes'" )
     Page<Product> findAllReleased(Pageable var1);

    @Query("select p from Product p where p.productName like %:searchString% and p.release='Yes'")
    Page<Product> findByProductName(Pageable var1,String searchString);

//      @Query(value="select p from Product p where p.id=:categoryId")
//      List<Product> findAllByCategoryId(long categoryId);
      List<Product> findAllByOrderByUpLoadedDate();
}

