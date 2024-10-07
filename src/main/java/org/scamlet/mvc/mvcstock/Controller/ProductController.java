package org.scamlet.mvc.mvcstock.Controller;

import jakarta.validation.Valid;
import org.scamlet.mvc.mvcstock.Entity.Product;
import org.scamlet.mvc.mvcstock.Entity.User;
import org.scamlet.mvc.mvcstock.Service.LogService;
import org.scamlet.mvc.mvcstock.Service.ProductService;
import org.scamlet.mvc.mvcstock.Service.UserService;
import org.scamlet.mvc.mvcstock.Util.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final LogService logService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, LogService logService, UserService userService) {
        this.productService = productService;
        this.logService = logService;
        this.userService = userService;
    }

    @GetMapping("/products")
    public String productList(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        // List<Product> products = productService.productList();
        Page<Product> productPage = productService.findPage(page,size);
        model.addAttribute("pageTitle", "Products");
        model.addAttribute("products", productPage);
        return "/product/product";
    }

    @GetMapping("/product/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Add Product");
        return "/product/add-product";
    }

    @PostMapping("/product/addProduct")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("pageTitle", "Add Product");
        model.addAttribute("product", product);

        if (bindingResult.hasErrors()) {

            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/product/add-product";
        }

        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("success", "Product added successfully");

        Authentication authentication = Session.getCurrentAuthentication();
        if (authentication != null) {
            User tempUser = userService.findByUsername(authentication.getName());
            if (tempUser != null) {
                logService.addLog(tempUser, "Add product("+product.getId()+") named : "+product.getName()+" quantity : "+product.getQuantity()+" price : "+product.getPrice()+" ");
            }
        }

        return "redirect:/product/add-product";
    }

    @GetMapping("/product/update-product")
    public String updateProductForm(@RequestParam("productId") Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isEmpty()) {
            model.addAttribute("pageTitle", "Home Page");
            return "redirect:/";
        }

        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Update Product");
        return "/product/update-product";
    }

    @PostMapping("/product/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("pageTitle", "Update Product");
        model.addAttribute("product", product);

        if (bindingResult.hasErrors()) {
            model.addAttribute("productId", product.getId());
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/product/update-product";
        }

        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("productId", product.getId());
        redirectAttributes.addFlashAttribute("success", "Product updated successfully");

        Authentication authentication = Session.getCurrentAuthentication();
        if (authentication != null) {
            User tempUser = userService.findByUsername(authentication.getName());
            if (tempUser != null) {
                logService.addLog(tempUser, "Update product("+product.getId()+") named : "+product.getName()+" quantity : "+product.getQuantity()+" price : "+product.getPrice()+" ");
            }
        }

        return "redirect:/product/update-product?productId=" + product.getId();
    }

    @GetMapping("/product/deleteProduct")
    public String deleteProductForm(@RequestParam("productId") Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isEmpty()) {
            return "redirect:/products";
        }

        Authentication authentication = Session.getCurrentAuthentication();
        if (authentication != null) {
            User tempUser = userService.findByUsername(authentication.getName());
            if (tempUser != null) {
                logService.addLog(tempUser, "Delete product("+product.get().getId()+") named : "+product.get().getName()+" quantity : "+product.get().getQuantity()+" price : "+product.get().getPrice()+" ");
            }
        }

        productService.deleteProduct(product.get());
        return "redirect:/products";
    }

}
