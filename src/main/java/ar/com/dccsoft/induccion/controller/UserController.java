package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.dto.UserRequest;
import ar.com.dccsoft.induccion.exception.DomainException;
import ar.com.dccsoft.induccion.exception.InvalidFormException;
import ar.com.dccsoft.induccion.exception.UserAlreadyExistsException;
import ar.com.dccsoft.induccion.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("create")
    public String getCreateUser() {
        return "users/create";
    }

    @PostMapping("create")
    public String postCreateUser(
            @Valid UserRequest userReq,
            BindingResult validation,
            RedirectAttributes redirectAttributes
    ) {

        if (validation.hasErrors()) {
            throw new InvalidFormException();
        }

        userService.createUser(userReq);

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/users/create";
    }

    @ExceptionHandler(DomainException.class)
    public String handleError(DomainException err, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", err.getMessage());
        return "redirect:/users/create";
    }
}
