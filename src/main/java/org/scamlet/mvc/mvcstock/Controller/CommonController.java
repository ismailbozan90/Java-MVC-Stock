package org.scamlet.mvc.mvcstock.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.scamlet.mvc.mvcstock.Service.LogService;
import org.scamlet.mvc.mvcstock.Service.ProductService;
import org.scamlet.mvc.mvcstock.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    private final ProductService productService;
    private final UserService userService;
    private final LogService logService;

    @Autowired
    public CommonController(ProductService productService, UserService userService, LogService logService) {
        this.productService = productService;
        this.userService = userService;
        this.logService = logService;
    }

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        if (request.getUserPrincipal() == null) {
            return "redirect:/login";
        }
        model.addAttribute("pageTitle", "Home Page");
        model.addAttribute("productCount", productService.productCount());
        model.addAttribute("userCount", userService.userCount());
        model.addAttribute("logCount", logService.logCount());
        return "index";
    }

}
