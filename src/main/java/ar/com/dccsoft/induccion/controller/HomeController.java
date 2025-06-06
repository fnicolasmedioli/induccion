package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String renderHomePage(Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            var authorities = authentication.getAuthorities();

            for (var authority : authorities) {
                String role = authority.getAuthority();
                if (role.equals("ROLE_Admins")) {
                    return "redirect:/gestion";
                }
            }

            return "redirect:/user";
        }

        return "index";
    }

}
