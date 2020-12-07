package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Bid;
import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.service.BidService;
import edu.miu.cs.auctionproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

//
//    @GetMapping("/getall")
//    private String getAllBids(){
//        List<Category> allCategories = categoryService.getAllCategories();
//        System.out.println(allCategories + "---------------");
//        return "Home";
//    }
//
//    @PostMapping("/save_categories")
//    private void saveCategory(Category category){
//        categoryService.save(category);
//    }
//
//    @GetMapping("/get_categoriesById")
//    private String getCategoriesById(Long id){
//        Optional<Category> category = categoryService.getCategoryById(id);
//        System.out.println(category + "---------------");
//        return "Home";
//    }
//    @DeleteMapping("/delete_categroy")
//    private void deleteCategory(Long id){
//        categoryService.deleteCategoryById(id);
//    }



    @GetMapping(value = {"edit/{categoryId}"})
    public String editCategory(@PathVariable long categoryId, Model model) {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        if (category.isPresent()) {
            model.addAttribute("categories", category.get());
            return "/editcategoryform";
        }
        return "/getall";
    }

    @PostMapping(value = {"/edit"})
    public String updateCategory(@Validated @ModelAttribute("category") Category category,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/editcategoryform";
        }
        categoryService.save(category);
        return "redirect:categories/getall";
    }

    @RequestMapping(value={"/getall"})
    public ModelAndView listCategorys(@RequestParam(defaultValue = "0") int pageNo, ModelAndView modelAndView) {
        List<Category> categorys = categoryService.getAllCategories();
        modelAndView.addObject("categories",categorys);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("categorysCount", categorys.size());
        modelAndView.setViewName("listcategory");
        return modelAndView;
    }

//    @GetMapping(value = {"/search"})
//    public ModelAndView searchStudents(@RequestParam String searchString) {
//        ModelAndView modelAndView = new ModelAndView();
//        List<Category> categorys = categoryService.searchCategory(searchString);
//        modelAndView.addObject("categorys", categorys);
//        modelAndView.addObject("searchString", searchString);
//        modelAndView.addObject("CategorysCount", categorys.size());
//        modelAndView.setViewName("Categorys");
//        return modelAndView;
//    }

    @RequestMapping(value = {"/new" })
    public String inputCategory(@ModelAttribute("category") Category category) {
        return "addcategory";
    }


    @RequestMapping(value = "/add")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "addcategory";
        }

        // save category here
        categoryService.save(category);
        model.addAttribute("category", category);

        return "redirect:/categories/getall";
    }
    @GetMapping(value = {"/delete/{categoryId}"})
    public String deleteStudent(@PathVariable Long categoryId, Model model) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/categories/getall";
    }
}
