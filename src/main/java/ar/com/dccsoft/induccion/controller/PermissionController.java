package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permissions")
@AllArgsConstructor
public class PermissionController {

    private final GroupService groupService;

    @GetMapping
    public String getPermissions(Model model) {

        model.addAttribute("groups", groupService.listGroupsWithOperations());

        return "permissions";
    }

}
