package edu.miu.cs.auctionproject.service;

import edu.miu.cs.auctionproject.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Category save(Category category);
    Optional<Category> getCategoryById(Long id);
    void deleteCategoryById(Long id);
    Category findAllByName(String name);

    List<Category> findAllCategoriesHasProduct(List<Category> categories);
}
