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

@Controller
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("list")
    public String getListado(Model model) {

        model.addAttribute("users", userService.getFullUsers());
        return "users/list";
    }

    @GetMapping("create")
    public String getCreateUser() {
        return "users/create";
    }

    @PostMapping("create")
    public String postCreateUser(
            @Valid UserRequest userReq,
            BindingResult validation,
            Model model
    ) {

        if (validation.hasErrors()) {
            throw new InvalidFormException();
        }

        userService.createUser(userReq);

        model.addAttribute("success", true);
        return "users/create";
    }

    @ExceptionHandler({
            UserAlreadyExistsException.class,
            InvalidFormException.class
    })
    public String handleUserAlreadyExists(DomainException err, Model model) {
        model.addAttribute("error", err.getMessage());
        return "users/create";
    }

}
