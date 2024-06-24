package com.cmc.demo.oauth.security.entity;

import com.cmc.demo.oauth.model.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyUserDetails implements UserDetails {
    private final Users users;
    private final List<String> permissionList;

    public MyUserDetails(Users users, List<String> permissionList) {
        this.permissionList = permissionList;
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (String permission : permissionList) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUserName();
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
        return users.isActive();
    }
}
