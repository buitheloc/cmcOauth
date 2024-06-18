package com.cmc.demo.oauth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PermissionAccess {
    public static boolean havePermissionAccess(String access){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for(Object obj : authentication.getAuthorities().toArray()){
            if (obj.toString().equals(access)) return true;
        }

        return false;
    }
}
