package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("list")
    public String getListado(Model model) {

        model.addAttribute("users", userService.getFullUsers());
        return "list";
    }

    @GetMapping("/")
    public String renderHomePage() {
        return "index";
    }

}
