package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void saveProduct(Product product);
    void deleteProduct(Long id);
    Optional<Product> findProductById(Long id);
    Page<Product> findAllProducts(int pageNo);
    Page<Product> searchProduct(int pageNo,String searchString);

    List<Product> findAllProductsList();

    List<Product> findAllProductsByCategory(long categoryId);
//    List<Product> findProductByCategoriesContains(Long categoryId);


}
