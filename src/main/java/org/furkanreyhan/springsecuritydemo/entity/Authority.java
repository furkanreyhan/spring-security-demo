package org.furkanreyhan.springsecuritydemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Collection<Role> roles;

    public Authority(String name) {
        this.name = name;
    }
}
