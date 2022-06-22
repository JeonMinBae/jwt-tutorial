package com.jwt.icraft.auth.dto;

import com.jwt.icraft.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String userId;
    private String userPassword;
    private Role userRole;
}
