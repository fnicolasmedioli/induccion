package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.dto.GroupRequest;
import ar.com.dccsoft.induccion.dto.PermissionRequest;
import ar.com.dccsoft.induccion.exception.InvalidFormException;
import ar.com.dccsoft.induccion.service.GroupService;
import ar.com.dccsoft.induccion.service.OperationService;
import ar.com.dccsoft.induccion.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("gestion")
public class GestionController {

    private final GroupService groupService;
    private final OperationService operationService;
    private final PermissionService permissionService;

    @GetMapping
    public String getGestion(Authentication authentication) {

        return "gestion/gestion";
    }

    @GetMapping("groups")
    public String getGroups(Model model) {

        model.addAttribute("groups", groupService.listGroupsWithUsers());

        return "gestion/groups";
    }

    @PostMapping("groups")
    public String postGroup(
            @Valid GroupRequest groupRequest,
            BindingResult validation,
            RedirectAttributes redirectAttributes
    ) {

        if (validation.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", new InvalidFormException().getMessage());
            return "redirect:/gestion/groups";
        }

        groupService.createGroup(groupRequest);

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/gestion/groups";
    }

    @GetMapping("permissions")
    public String getPermissions(Model model) {

        model.addAttribute("groups", groupService.listGroupsWithOperations());
        model.addAttribute("operations", operationService.listOperations());

        return "gestion/permissions";
    }

    @PostMapping("permissions")
    public String postPermission(
            @Valid PermissionRequest permissionRequest,
            BindingResult validation,
            RedirectAttributes redirectAttributes
    ) {

        if (validation.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", new InvalidFormException().getMessage());
            return "redirect:/gestion/permissions";
        }

        permissionService.addPermission(permissionRequest);

        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/gestion/permissions";
    }

}
