package com.red_social.auth_service.security;


import com.red_social.auth_service.model.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Login login;

    // ✅ Roles / Authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (login.getRol() != null) {
            return Collections.singletonList(
                    new SimpleGrantedAuthority(login.getRol().getNombre())
            );
        }
        return Collections.emptyList();
    }

    // ✅ Password (puede ser null si viene de Google)
    @Override
    public String getPassword() {
        return login.getPassword();
    }

    @Override
    public String getUsername() {
        // 1️⃣ Si tiene username, usarlo
        if (login.getUsername() != null && !login.getUsername().isBlank()) {
            return login.getUsername();
        }

        // 2️⃣ Si no tiene username, usar email
        if (login.getEmail() != null && !login.getEmail().isBlank()) {
            return login.getEmail();
        }

        // 3️⃣ Si tampoco tiene email, usar teléfono
        return login.getTelefono();
    }


    // ✅ Cuenta nunca expira (puedes ajustar si quieres)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // ✅ Bloqueo basado en el status del usuario
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

    // ✅ Activo solo si no está bloqueado
    @Override
    public boolean isEnabled() {
        if (login.getEstadoUsuario() != null) {
            return !"BLOCKED".equalsIgnoreCase(login.getEstadoUsuario().getNombre());
        }
        return true;
    }

    // ✅ Getter para acceder al usuario real si se necesita
    public Login getLogin() {
        return login;
    }

    // ✅ Override de toString (útil para depuración)
    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "username='" + getUsername() + '\'' +
                ", role=" + (login.getRol() != null ? login.getRol().getNombre() : "SIN ROL") +
                ", estado=" + (login.getEstadoUsuario() != null ? login.getEstadoUsuario().getNombre() : "N/A") +
                '}';
    }
}