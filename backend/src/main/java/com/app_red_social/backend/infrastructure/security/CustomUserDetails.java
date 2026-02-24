package com.app_red_social.backend.infrastructure.security;


import com.app_red_social.backend.domain.model.Login;
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
        return login.getRol() != null
                ? Collections.singletonList(new SimpleGrantedAuthority(login.getRol().getCodigo()))
                : Collections.emptyList();
    }

    @Override
    public String getPassword() { return login.getPassword(); }

    @Override
    public String getUsername() {
        if (login.getUsername() != null) return login.getUsername();
        if (login.getEmail() != null) return login.getEmail();
        if (login.getTelefono() != null) return login.getTelefono();
        return "unknown_user";
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return !"BLOQUEADO".equalsIgnoreCase(login.getEstadoUsuario().getNombre()); }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return "ACTIVO".equalsIgnoreCase(login.getEstadoUsuario().getNombre()); }
}