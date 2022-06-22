package com.jwt.icraft.user.service;

import com.jwt.icraft.user.entity.IUserRepository;
import com.jwt.icraft.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository iUserRepository;

    @Transactional(readOnly = true)
    public UserEntity getUser(Long userIndex){
        return iUserRepository.findByUserIndex(userIndex).orElse(null);
    }

}
