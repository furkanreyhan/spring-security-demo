package org.furkanreyhan.springsecuritydemo.loader;

import jakarta.transaction.Transactional;
import org.furkanreyhan.springsecuritydemo.entity.Authority;
import org.furkanreyhan.springsecuritydemo.entity.Role;
import org.furkanreyhan.springsecuritydemo.entity.User;
import org.furkanreyhan.springsecuritydemo.repository.AuthorityRepository;
import org.furkanreyhan.springsecuritydemo.repository.RoleRepository;
import org.furkanreyhan.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Authority readAuthority
                = createAuthorityIfNotFound("READ");
        Authority writeAuthority
                = createAuthorityIfNotFound("WRITE");

        List<Authority> adminAuthorities = Arrays.asList(
                readAuthority, writeAuthority);
        createRoleIfNotFound("ADMIN", adminAuthorities);

        Role adminRole = roleRepository.findByName("ADMIN");
        User user = new User();
        user.setFirstName("Furkan");
        user.setLastName("Reyha");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("furkan@mail.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    Authority createAuthorityIfNotFound(String name) {

        Authority authority = authorityRepository.findByName(name);
        if (authority == null) {
            authority = new Authority(name);
            authorityRepository.save(authority);
        }
        return authority;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Authority> authorities) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }
        return role;
    }
}
