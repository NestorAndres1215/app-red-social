package com.app_red_social.backend.infrastructure.security;


import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.port.repository.LoginRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepositoryPort loginRepository;

    @Override
    public UserDetails loadUserByUsername(String loginInput) {
        Login login = loginRepository.findByUsername(loginInput)
                .orElseGet(() -> loginRepository.findByEmail(loginInput)
                        .orElseGet(() -> loginRepository.findByTelefono(loginInput)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                        ));
        return new CustomUserDetails(login);
    }
}