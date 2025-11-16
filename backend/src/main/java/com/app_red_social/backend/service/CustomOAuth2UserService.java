package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.Roles;
import com.app_red_social.backend.dto.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    private final UsuarioService usuarioService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        UsuarioRequest user = UsuarioRequest.builder()
                .email((String) attributes.get("email"))
                .photoUrl((String) attributes.get("picture"))
                .nombre((String) attributes.get("given_name"))
                .apellido((String) attributes.get("family_name"))
                .provider("google")
                .rol(Roles.ROLE_USER)
                .build();

        return (OAuth2User) usuarioService.saveOrUpdateGoogleUser(user);
    }

}
