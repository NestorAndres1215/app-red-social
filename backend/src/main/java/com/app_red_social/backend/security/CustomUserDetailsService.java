package com.app_red_social.backend.security;


import com.app_red_social.backend.constants.messages.GlobalErrorMessages;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.repository.LoginRepository;
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
                                .orElseThrow(() -> new ResourceNotFoundException(GlobalErrorMessages.LOGIN_NO_VALIDO))
                        )
                );

        return new CustomUserDetails(user);
    }
}
