package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("listado")
    public String getListado(Model model) {

        model.addAttribute("users", userService.getFullUsers());
        return "users/listado";

    }

}
