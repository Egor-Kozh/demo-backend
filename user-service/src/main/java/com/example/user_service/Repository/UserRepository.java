package com.example.user_service.Repository;

import com.example.user_service.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "select * from users\n" +
            "where user_id = :userId;", nativeQuery = true)
    Optional<UserEntity> getUserById(Long userId);
}
