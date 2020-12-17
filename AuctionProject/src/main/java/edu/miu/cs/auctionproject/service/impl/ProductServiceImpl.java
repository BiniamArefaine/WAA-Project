package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.domain.User;
import edu.miu.cs.auctionproject.repository.ProductRepository;
import edu.miu.cs.auctionproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return productRepository.findAllReleased(PageRequest.of(pageNo, 2, Sort.by("startingPrice")));
    }


    @Override
    public Page<Product> searchProduct(int pageNo, String searchString) {
        return productRepository.findByProductName(PageRequest.of(pageNo, 2, Sort.by("productName")), searchString);
    }

    @Override
    public List<Product> findAllProductsList() {
        return productRepository.findAll();
    }

    //    @Override
//    public List<Product> findAllProductsByCategory(long categoryId) {
//
//        return productRepository.findAllByCategoryId(categoryId);
//    }
    @Override
    public List<Product> findAllProductsByCategory(long categoryId) {

        List<Product> pro = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            List<Category> categoriesWithProduct = new ArrayList<>();
            categoriesWithProduct.addAll(product.getCategories());
            for (Category c : categoriesWithProduct) {
                if (c.getId() == categoryId) {
                    pro.add(product);
                }
            }

        }
        return pro;

    }

    @Override
    public List<Product> findWonProducts(User user) {
        return (user.getWonProducts().stream().filter(product -> product.isPaidInFull() == false)).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByUploadedDate() {
        return productRepository.findAllByOrderByUpLoadedDate();
    }

    @Override
    public List<Product> getProductSold(User user) {
        return user.getProduct().stream().filter(product -> product.isSold() == true).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllPaidProducts(User user) {
        return user.getWonProducts().stream().filter(product -> product.isPaidInFull() == true).collect(Collectors.toList());
    }

    @Override
    public Boolean checkSetDepsoit(Product product) {

        return product.getStartingPrice() * 0.01 > product.getDepositpayment();

    }

    @Override
    public double calculateDepositPayment(Product product) {
        return product.getStartingPrice() * 0.01;
    }

    @Override
    public User findUserByProductId(long productId) {
        return productRepository.findUserByProductId(productId);
    }

}
