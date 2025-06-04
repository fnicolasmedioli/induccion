package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.dto.PermissionRequest;
import ar.com.dccsoft.induccion.exception.DomainException;
import ar.com.dccsoft.induccion.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/permissions")
@AllArgsConstructor
public class PermissionRestController {

    private final PermissionService permissionService;

    @DeleteMapping
    public ResponseEntity<Map<String, Boolean>> deletePermission(
            @RequestBody PermissionRequest permissionRequest
    ) {

        permissionService.deletePermission(permissionRequest);

        Map<String, Boolean> result = new HashMap<>();
        result.put("success", true);

        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, Boolean>> handleError(DomainException err) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("success", false);

        return ResponseEntity.ok(result);
    }

}
