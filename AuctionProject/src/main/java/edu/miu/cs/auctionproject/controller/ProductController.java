package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.service.CategoryService;
import edu.miu.cs.auctionproject.service.ProductService;
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
@RequestMapping(value = {"/product"})
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

//    @PostMapping(value = {"/add"})
//    public void addNewProduct(@RequestBody Product product) {
//        System.out.println(product.toString());
//        productService.saveProduct(product);
//    }

    @GetMapping(value = {"/get/{id}"})
    public Optional<Product> getProductbyId(@PathVariable(value = "id") Long id) {
        return productService.findProductById(id);
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public void deleteProductById(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
    }

//    @GetMapping(value = {"/getall"})
//    public List<Product> getAllProducts() {
//        return productService.findAllProducts();
//    }

    @GetMapping(value = {"edit/{productId}"})
    public String editProduct(@PathVariable long productId, Model model) {
        Optional<Product> product = productService.findProductById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("categories",categoryService.getAllCategories());
            return "/productEdit";
        }
        return "listproduct";
    }

    @PostMapping(value = {"/edit"})
    public String updateProduct(@Validated @ModelAttribute("product") Product product,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/editproductform";
        }
        productService.saveProduct(product);
        return "redirect:/product/getall";
    }

    @GetMapping(value = {"/getDetails/{id}"})
    public ModelAndView getProductDetailsId(@PathVariable(value = "id") Long id, ModelAndView modelAndView) {
        Optional<Product> product = productService.findProductById(id);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("bookingDetails");
        return modelAndView;
    }

    //biniam-dave
    @RequestMapping(value={"/getall"})
    public ModelAndView listProducts(@RequestParam(defaultValue = "0") int pageNo,ModelAndView modelAndView) {
        List<Product> products = productService.findAllProducts();
        modelAndView.addObject("products",products);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("productsCount", products.size());
        modelAndView.setViewName("listproduct");
        return modelAndView;
    }

    @GetMapping(value = {"/search"})
    public ModelAndView searchStudents(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = productService.searchProduct(searchString);
        modelAndView.addObject("products", products);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("ProductsCount", products.size());
        modelAndView.setViewName("Products");
        return modelAndView;
    }

    @RequestMapping(value = {"/new" })
    public String inputProduct(@ModelAttribute("product") Product product,Model model) {
        List<Category>categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "addproduct";
    }


    @RequestMapping(value = "/add")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("==========================");
            return "addproduct";
        }

        // save product here
        productService.saveProduct(product);
        model.addAttribute("product", product);

        return "redirect:/product/getall";
    }
    @GetMapping(value = {"/delete/{productId}"})
    public String deleteStudent(@PathVariable Long productId, Model model) {
        productService.deleteProduct(productId);
        return "redirect:/product/getall";
    }
}