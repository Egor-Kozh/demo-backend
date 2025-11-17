package com.example.auth_service.Repository;

import com.example.auth_service.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "select * from users\n" +
            "where email = :email", nativeQuery = true)
    UserEntity findUserByEmail(String email);
}
