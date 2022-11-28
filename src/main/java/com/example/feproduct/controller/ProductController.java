package com.example.feproduct.controller;

import com.example.feproduct.model.Category;
import com.example.feproduct.model.Pageable;
import com.example.feproduct.model.Product;
import com.example.feproduct.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private BaseService<Product> productService;

    @Autowired
    private BaseService<Category> categoryService;

    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        List<Category> listCategories = categoryService.getAllList();
        model.addAttribute("product", new Product());
        model.addAttribute("listCategories", listCategories);
        return "product_form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("imageProduct") MultipartFile imageProduct, RedirectAttributes attributes) {
        Category category = new Category();
        category.setId(Integer.valueOf(product.getCategory_id()));
        product.setCategory(category);
        productService.saveOrEditWithImg(imageProduct, product);
        attributes.addFlashAttribute("message", "Saved successfully!");
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getOneById(id);
        if (product != null) {
            model.addAttribute("product", product);
        }
        if (product.getCategory() != null) {
            product.setCategory_id(product.getCategory().getId().toString());
        }
        List<Category> listCategories = categoryService.getAllList();
        model.addAttribute("listCategories", listCategories);
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String DeleteProduct(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping("")
    public String showDataWithPage(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "limit", defaultValue = "5") Integer limit,
                                   @RequestParam(value = "sortname" ,defaultValue = "ASC") String sortName,
                                   @RequestParam(value = "sortby", defaultValue = "id") String sortBy,
                                    @RequestParam(value = "query", required = false) String query) {
        Pageable pageable = new Pageable(page, limit, sortName, sortBy);
        StringBuilder message = new StringBuilder("");
        List<Product> products = productService.getAllListPagingAndSearch(pageable,query,message);
        model.addAttribute("message", message);
        model.addAttribute("CurrentPage", page);
        model.addAttribute("query", query);
        model.addAttribute("limit", limit);
        model.addAttribute("sortName", sortName);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("TotalItem", pageable.getTotalItem());
        if (pageable.getTotalPage()==null){
            pageable.setTotalPage(0);
        }
        model.addAttribute("TotalPage", pageable.getTotalPage());
        model.addAttribute("listProducts", products);
        return "products";
    }
}
