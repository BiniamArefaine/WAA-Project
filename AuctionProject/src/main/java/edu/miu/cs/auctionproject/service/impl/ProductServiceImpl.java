package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.repository.ProductRepository;
import edu.miu.cs.auctionproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProduct(String searchString) {
        return productRepository.findAllById(Collections.singleton(Long.parseLong(searchString)));

    }

    //biniamdave
//    public List<Product> findAllByAscPrice(){
//        return productRepository.findAllByStartingPriceOrderByStartingPriceAsc();
//    }
//
//    //
//    public List<Product> findAllByDescPrice(){
//        return productRepository.findAllByStartingPriceOrderByStartingPriceDesc();
//    }
//
//    //
//    public List<Product> uploadedDateDesc(){
//        return productRepository.findAllByStartingPriceOrderByUpLoadedDateDesc();
//    }

}
