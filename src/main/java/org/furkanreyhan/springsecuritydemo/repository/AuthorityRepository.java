package org.furkanreyhan.springsecuritydemo.repository;

import org.furkanreyhan.springsecuritydemo.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findByName(String name);
}
