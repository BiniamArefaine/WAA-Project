package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.repository.ProductRepository;
import edu.miu.cs.auctionproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Page<Product> findAllProducts(int pageNo) {
        return  productRepository.findAll(PageRequest.of(pageNo,6, Sort.by("productName")));
    }




    @Override
    public Page<Product> searchProduct(int pageNo,String searchString) {
        return productRepository.findAll(PageRequest.of(pageNo,6,Sort.by(searchString)));
    }
}
