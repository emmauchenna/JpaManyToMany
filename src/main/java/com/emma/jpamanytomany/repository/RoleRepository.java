package com.emma.jpamanytomany.repository;

import com.emma.jpamanytomany.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
