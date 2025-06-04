package ar.com.dccsoft.induccion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
