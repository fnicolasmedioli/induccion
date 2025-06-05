package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.dto.UserRequest;
import ar.com.dccsoft.induccion.exception.DomainException;
import ar.com.dccsoft.induccion.exception.InvalidFormException;
import ar.com.dccsoft.induccion.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("login")
    public String getLogin() {
        return "auth/login";
    }

    @GetMapping("signup")
    public String getSignup() {
        return "auth/signup";
    }

    @PostMapping("signup")
    public String postCreateUser(
            @Valid UserRequest userReq,
            BindingResult validation,
            RedirectAttributes redirectAttributes
    ) {

        if (validation.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", new InvalidFormException().getMessage());
            return "redirect:/auth/signup";
        }

        try {
            userService.createUser(userReq);
        } catch (DomainException err) {
            redirectAttributes.addFlashAttribute("error", err.getMessage());
            return "redirect:/auth/signup";
        }

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/auth/signup";
    }

}
