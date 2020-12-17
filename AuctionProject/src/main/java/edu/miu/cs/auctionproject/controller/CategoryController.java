package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.service.CategoryService;
import edu.miu.cs.auctionproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    HttpSession session;


    @GetMapping(value = {"edit/{categoryId}"})
    public String editCategory(@PathVariable long categoryId, Model model) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "secured/admin/editcategoryform";
        }
        return "secured/admin/listcategory";
    }

    @PostMapping(value = {"/edit"})
    public String updateCategory(@Validated @ModelAttribute("category") Category category,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "secured/admin/editcategoryform";
        }
        category = categoryService.save(category);
        return "redirect:/categories/getall";
    }

    @RequestMapping(value = {"/getall"})
    public ModelAndView listCategories(@RequestParam(defaultValue = "0") int pageNo, ModelAndView modelAndView) {
        List<Category> categories = categoryService.getAllCategories();
        List<Category> categoriesProducts = categoryService.findAllCategoriesHasProduct(categories);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("categoriesProducts", categoriesProducts);
        modelAndView.addObject("categorysCount", categories.size());
        modelAndView.setViewName("/secured/admin/listcategory");
        return modelAndView;
    }


    @RequestMapping(value = {"/getallforcustomer"})
    public ModelAndView listCategoriescustomer(@RequestParam(defaultValue = "0") int pageNo, ModelAndView modelAndView) {
        List<Category> categories = categoryService.getAllCategories();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("categorysCount", categories.size());
        modelAndView.setViewName("listcategory");
        return modelAndView;
    }

    @RequestMapping(value = {"/new"})
    public String inputCategory(@ModelAttribute("category") Category category) {
        return "/secured/admin/addcategory";
    }


    @RequestMapping(value = "/add")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "secured/admin/addcategory";
        }
        // save category here
        categoryService.save(category);
        System.out.println("----in category");
        System.out.println(session.getAttribute("userId").toString());
        model.addAttribute("category", category);

        return "redirect:/categories/getall";
    }

    @GetMapping(value = {"/delete/{categoryId}"})
    public String deleteCategory(@PathVariable Long categoryId, ModelAndView model) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/categories/getall";
    }
    @GetMapping
    public String logInFailed(){

        return "notVerified";
    }
}


