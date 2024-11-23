package com.rifqy.project.ecommerce.e_commerce.authentication.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rifqy.project.ecommerce.e_commerce.applicationuser.model.ApplicationUser;

import lombok.Getter;

public class UserPrincipal implements UserDetails {
    @Getter
    private Long id;

    @Getter
    private String name;

    private String username;

    @Getter
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(Long id, String name, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetails build(ApplicationUser applicationUser) {
        List<GrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority(applicationUser.getRoleName().name()));
        return new UserPrincipal(
                applicationUser.getId(),
                applicationUser.getName(),
                applicationUser.getUsername(),
                applicationUser.getEmail(),
                applicationUser.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}