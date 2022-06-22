package com.jwt.icraft.user.service;

import com.jwt.icraft.user.adapter.UserAdapter;
import com.jwt.icraft.user.entity.IUserRepository;
import com.jwt.icraft.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JTUserDetailsService implements UserDetailsService {

    private final IUserRepository iUserRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserEntity userEntity = iUserRepository.findByUserId(userId).orElse(null);

        if(Objects.isNull(userEntity)){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserAdapter(userEntity);
    }
}
