package com.jwt.icraft.user.entity;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findByUserIndex(Long userIndex);

    UserEntity save(UserEntity entity);

}
