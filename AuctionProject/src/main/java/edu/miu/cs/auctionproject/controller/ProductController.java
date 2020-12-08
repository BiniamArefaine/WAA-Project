package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.FileUploadUtil;
import edu.miu.cs.auctionproject.domain.Category;
import edu.miu.cs.auctionproject.domain.Product;
import edu.miu.cs.auctionproject.service.CategoryService;
import edu.miu.cs.auctionproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/productEdit";
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
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,@RequestParam("image") MultipartFile multipartFile,
                              Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addproduct";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.addPhoto(fileName);



        String uploadDir = "src/main/resources/static/images/product-photos/" + product.getId();
        System.out.println(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        System.out.println(product.getPhotosImagePath());
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
//    @RequestMapping(value="/employee_save", method = RequestMethod.POST)
//    public String saveEmployee(@Valid @ModelAttribute("employee")  Employee employee, BindingResult bindingResult,
//                               Model model, HttpServletRequest request) throws FileNotFoundException {
//
//        if (bindingResult.hasErrors()) {
//            return "EmployeeForm";
//        }
//
//        MultipartFile image = employee.getImage();
//        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//
//        if (image!=null && !image.isEmpty()) {
//            try {
//                image.transferTo(new File(rootDirectory+"//images//"+ employee.getId() + ".png"));
//            } catch (Exception e) {
//                System.out.println(e);
//                throw new FileNotFoundException("Unable to save image: " + image.getOriginalFilename() );
//            }
//        }
//        model.addAttribute("employee", employee);
//
//        return "EmployeeDetails";
//    }
}