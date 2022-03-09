package com.sabitov.dto;

import com.sabitov.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {

    @NotBlank(message = "Should ba field")
    private String name;

    @NotBlank(message = "Should be field")
    private String email;

    @Size(message = "Bad formed password: ${validatedValue}",
            min = 3)
    @NotBlank(message = "Should be field")
    private String password;

    public Account form(CreateUserDto dto) {
        return Account.builder().name(dto.name).email(dto.email).password(dto.password).build();
    }
}
