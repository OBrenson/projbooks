package com.boi.security;

import com.boi.domain.AppUser;
import com.boi.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            if(userName.equals(auth.getName())) {
                return auth;
            }
        }

        MessageDigest md ;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e.getMessage());
        }
        md.update(((String)authentication.getCredentials()).getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();

        AppUser user = appUserRepository.findByName(userName);
        if(user == null) {
            throw new BadCredentialsException("User or password is wrong");
        }
        if(!user.getPassword().equals(hash)) {
            throw new BadCredentialsException("User or password is wrong");
        }

        return new UsernamePasswordAuthenticationToken(userName, (String)authentication.getCredentials(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
