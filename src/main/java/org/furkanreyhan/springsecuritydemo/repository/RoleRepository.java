package org.furkanreyhan.springsecuritydemo.repository;

import org.furkanreyhan.springsecuritydemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleAdmin);
}
