package org.scamlet.mvc.mvcstock.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("pageTitle", "Login");
        if (request.getUserPrincipal() != null) {
            return "redirect:/";
        }
        return "/auth/login";
    }

}
