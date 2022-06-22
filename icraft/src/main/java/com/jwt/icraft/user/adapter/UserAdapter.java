package com.jwt.icraft.user.adapter;


import com.jwt.icraft.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
@Setter
public class UserAdapter extends User {

    private UserEntity userEntity;

    public UserAdapter(UserEntity entity){
        super(
            entity.getUserId(),
            entity.getUserPassword(),
            Collections.singleton(new SimpleGrantedAuthority(entity.getUserRole().toString()))
        );
        this.userEntity = entity;
    }
}
