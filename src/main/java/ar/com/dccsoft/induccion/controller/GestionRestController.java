package ar.com.dccsoft.induccion.controller;

import ar.com.dccsoft.induccion.dto.PermissionRequest;
import ar.com.dccsoft.induccion.exception.DomainException;
import ar.com.dccsoft.induccion.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gestion/api/permissions")
@AllArgsConstructor
public class GestionRestController {

    private final PermissionService permissionService;

    @DeleteMapping
    public ResponseEntity<Map<String, Boolean>> deletePermission(
            @RequestBody PermissionRequest permissionRequest
    ) {

        try {
            permissionService.deletePermission(permissionRequest);
        } catch (DomainException err) {

            Map<String, Boolean> result = new HashMap<>();
            result.put("success", false);

            return ResponseEntity.ok(result);
        }

        Map<String, Boolean> result = new HashMap<>();
        result.put("success", true);

        return ResponseEntity.ok(result);
    }
}
