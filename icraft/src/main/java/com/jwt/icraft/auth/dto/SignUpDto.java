package com.jwt.icraft.auth.dto;

import com.jwt.icraft.user.Role;
import com.jwt.icraft.user.entity.UserEntity;
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

    public UserEntity createUserEntity(String encodedPassword){
        return UserEntity.builder()
            .userId(this.getUserId())
            .userPassword(encodedPassword)
            .userRole(this.getUserRole())
            .build();
    }
}
