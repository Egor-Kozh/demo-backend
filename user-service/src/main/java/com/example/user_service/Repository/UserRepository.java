package com.example.user_service.Repository;

import com.example.user_service.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select * from users\n" +
            "where user_id = :userId;", nativeQuery = true)
    Optional<UserEntity> getUserById(Long userId);
}
