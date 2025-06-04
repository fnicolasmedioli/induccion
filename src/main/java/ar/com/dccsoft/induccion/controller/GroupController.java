package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.dto.GroupRequest;
import ar.com.dccsoft.induccion.exception.DomainException;
import ar.com.dccsoft.induccion.exception.InvalidFormException;
import ar.com.dccsoft.induccion.exception.UserAlreadyExistsException;
import ar.com.dccsoft.induccion.service.GroupService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("groups")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public String getGroups(Model model) {

        model.addAttribute("groups", groupService.listGroupsWithUsers());

        return "groups";
    }

    @PostMapping
    public String postGroup(
            @Valid GroupRequest groupRequest,
            BindingResult validation,
            RedirectAttributes redirectAttributes
    ) {

        if (validation.hasErrors()) {
            throw new InvalidFormException();
        }

        groupService.createGroup(groupRequest);

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/groups";
    }

    @ExceptionHandler(DomainException.class)
    public String handleError(
            DomainException err,
            RedirectAttributes redirectAttributes
        )
    {
         redirectAttributes.addFlashAttribute("error", err.getMessage());
         return "redirect:/groups";
    }

}
