package com.boi.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser extends BaseEntity {

    public AppUser(UUID id) {
        super(id);
    }

    @Column(name = "name")
    private String name;

    @Column(name = "password", unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
