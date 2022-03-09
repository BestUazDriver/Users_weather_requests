package com.sabitov.dto;

import com.sabitov.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleUserDto {

        private Long id;
        private String role;
        private String email;
        private String password;
        private String name;

    public Account form(SimpleUserDto dto) {
        return Account.builder().id(dto.id).name(dto.name).email(dto.email).password(dto.password).role(dto.role).build();
    }

    public static SimpleUserDto reverseForm(Account account){
        return SimpleUserDto.builder().id(account.getId()).name(account.getName()).email(account.getEmail()).password(account.getPassword()).build();
    }

}
