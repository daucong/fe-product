package com.example.feproduct.controller;

import com.example.feproduct.model.Category;
import com.example.feproduct.model.Pageable;
import com.example.feproduct.model.Product;
import com.example.feproduct.service.CategoryService;
import com.example.feproduct.service.ProductService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        productService.saveProduct(imageProduct, product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String listProduct(Model model) {
        return showData(model,1,5,"ASC","id");
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findProductById(id);
        if(product != null){
            model.addAttribute("product", product);
        }
        if(product.getCategory()!=null){
            product.setCategory_id(product.getCategory().getId().toString());
        }
        List<Category> listCategories = categoryService.getAllCategory();
        model.addAttribute("listCategories", listCategories);
        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String DeleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/products/paging")
    public String showData(Model model,@RequestParam("page") Integer page,@RequestParam("limit") Integer limit,@RequestParam("sortname") String sortName,@RequestParam("sortby") String sortBy) {
        Pageable pageable = new Pageable(page,limit,sortName,sortBy,null);
        List<Product> products = productService.getAllProductPaging(pageable);
        model.addAttribute("CurrentPage",page);
        limit = 5;
        sortName="ASC";
        model.addAttribute("limit",limit);
        model.addAttribute("sortName",sortName);
        model.addAttribute("sortBy",sortBy);
        model.addAttribute("TotalItem", productService.totalItem());
        model.addAttribute("TotalPage",pageable.getTotalPage());
        model.addAttribute("listProducts", products);
        return "products";
    }

}
