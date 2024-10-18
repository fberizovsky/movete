
package com.example.movete.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetearPasswordDTO {

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
