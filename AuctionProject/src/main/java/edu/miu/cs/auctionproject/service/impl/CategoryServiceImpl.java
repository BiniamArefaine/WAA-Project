package edu.miu.cs.auctionproject.service.impl;

import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.repository.CategoryRepository;
import edu.miu.cs.auctionproject.service.CategoryService;
import edu.miu.cs.auctionproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductService productService;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        category.setName(category.getName().toUpperCase());
       return  categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findAllByName(String name) {
        return categoryRepository.findAllByName(name);
    }

    @Override
    public List<Category> findAllCategoriesHasProduct(List<Category> categories) {
        List<Category>categoriesWithProduct=new ArrayList<>();
        List<Product> products=productService.findAllProductsList();
        for(Product product:products){
           categoriesWithProduct.addAll(product.getCategories());
        }
        return categoriesWithProduct;
    }


}
