package ar.com.dccsoft.induccion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
