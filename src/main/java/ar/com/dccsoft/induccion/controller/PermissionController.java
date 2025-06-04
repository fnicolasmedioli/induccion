package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.dto.GroupRequest;
import ar.com.dccsoft.induccion.dto.PermissionRequest;
import ar.com.dccsoft.induccion.entity.Group;
import ar.com.dccsoft.induccion.entity.Operation;
import ar.com.dccsoft.induccion.exception.DomainException;
import ar.com.dccsoft.induccion.exception.InvalidFormException;
import ar.com.dccsoft.induccion.service.GroupService;
import ar.com.dccsoft.induccion.service.OperationService;
import ar.com.dccsoft.induccion.service.PermissionService;
import ar.com.dccsoft.induccion.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/permissions")
@AllArgsConstructor
public class PermissionController {

    private final GroupService groupService;
    private final OperationService operationService;
    private final PermissionService permissionService;

    @GetMapping
    public String getPermissions(Model model) {

        model.addAttribute("groups", groupService.listGroupsWithOperations());
        model.addAttribute("operations", operationService.listOperations());

        return "permissions";
    }

    @PostMapping
    public String postPermission(
            @Valid PermissionRequest permissionRequest,
            BindingResult validation,
            RedirectAttributes redirectAttributes
    ) {

        if (validation.hasErrors()) {
            throw new InvalidFormException();
        }

        permissionService.addPermission(permissionRequest);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/permissions";
    }


    @ExceptionHandler(DomainException.class)
    public String handleError(
            DomainException err,
            RedirectAttributes redirectAttributes
    )
    {
        redirectAttributes.addFlashAttribute("error", err.getMessage());
        return "redirect:/permissions";
    }

}
