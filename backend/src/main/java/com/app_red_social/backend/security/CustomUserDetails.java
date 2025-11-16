package com.app_red_social.backend.security;

import com.app_red_social.backend.model.Login;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Login login;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (login.getRol() != null) {
            return Collections.singletonList(
                    new SimpleGrantedAuthority(login.getRol().getNombre())
            );
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return login.getPassword();
    }

    @Override
    public String getUsername() {
        if (login.getUsername() != null && !login.getUsername().isBlank()) {
            return login.getUsername();
        }

        if (login.getEmail() != null && !login.getEmail().isBlank()) {
            return login.getEmail();
        }

        return login.getTelefono();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (login.getEstadoUsuario() != null) {
            return !"BLOCKED".equalsIgnoreCase(login.getEstadoUsuario().getNombre());
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (login.getEstadoUsuario() != null) {
            return !"BLOCKED".equalsIgnoreCase(login.getEstadoUsuario().getNombre());
        }
        return true;
    }

}
