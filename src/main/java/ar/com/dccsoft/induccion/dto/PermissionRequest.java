package ar.com.dccsoft.induccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionRequest {

    @NotNull
    private Long groupid;

    @NotNull
    private Long operationid;

}
