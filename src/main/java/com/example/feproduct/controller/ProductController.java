package com.example.feproduct.controller;

import com.example.feproduct.model.Category;
import com.example.feproduct.model.Product;
import com.example.feproduct.service.CategoryService;
import com.example.feproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        List<Category> listCategories = categoryService.getAllCategory();
        model.addAttribute("product", new Product());
        model.addAttribute("listCategories", listCategories);
        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("imageProduct") MultipartFile imageProduct) {
        Category category = new Category();
        category.setId(Integer.valueOf(product.getCategory_id()));
        product.setCategory(category);
        try {
            product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productService.saveProduct(imageProduct, product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listProduct(Model model) {
        List<Product> listProducts = productService.getAllProduct();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);

        List<Category> listCategories = categoryService.getAllCategory();
        model.addAttribute("listCategories", listCategories);
        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String DeleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }


}
