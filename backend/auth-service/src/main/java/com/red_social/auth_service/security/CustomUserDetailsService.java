package com.red_social.auth_service.security;


import com.red_social.auth_service.constants.AuthConstants;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.Login;
import com.red_social.auth_service.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepository loginRepository;


    @Override
    public UserDetails loadUserByUsername(String login) {

        Login user = loginRepository.findByUsername(login)
                .orElseGet(() -> loginRepository.findByEmail(login)
                        .orElseGet(() -> loginRepository.findByTelefono(login)
                                .orElseThrow(() -> new ResourceNotFoundException(AuthConstants.USUARIO_NO_VALIDO))
                        )
                );

        return new CustomUserDetails(user);
    }

}