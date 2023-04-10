package xyz.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.api.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    
}