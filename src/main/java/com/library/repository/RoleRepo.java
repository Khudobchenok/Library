package com.library.repository;

import com.library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Создание репозитория для доступа к БД
 */

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleById (Long id);
}
