package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.service.BidService;
import edu.miu.cs.auctionproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @GetMapping("/getall_categories")
    private String getAllBids(){
        List<Category> allCategories = categoryService.getAllCategories();
        System.out.println(allCategories + "---------------");
        return "Home";
    }

    @PostMapping("/save_categories")
    private void saveCategory(Category category){
        categoryService.save(category);
    }

    @GetMapping("/get_categoriesById")
    private String getCategoriesById(Long id){
        Optional<Category> category = categoryService.getCategoryById(id);
        System.out.println(category + "---------------");
        return "Home";
    }
    @DeleteMapping("/delete_categroy")
    private void deleteCategory(Long id){
        categoryService.deleteCategoryById(id);
    }
}
