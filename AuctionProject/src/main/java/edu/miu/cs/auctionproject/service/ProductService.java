package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void saveProduct(Product product);
    void deleteProduct(Long id);
    Optional<Product> findProductById(Long id);
    List<Product> findAllProducts();
    List<Product> searchProduct(String searchString);
//    List<Product> findProductByCategoriesContains(Long categoryId);


}
