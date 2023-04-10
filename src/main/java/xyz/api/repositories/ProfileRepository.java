package xyz.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.api.entities.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    
}