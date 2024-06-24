package com.cmc.demo.oauth.security.service;

import com.cmc.demo.oauth.security.entity.MyUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();

        return Optional.of(user.getUsername());
    }
}
