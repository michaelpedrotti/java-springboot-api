package xyz.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.api.entities.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {}