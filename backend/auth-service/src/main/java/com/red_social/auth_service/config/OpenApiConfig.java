package com.red_social.auth_service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Security")
                        .version("1.0")
                        .description("""
                                Documentación de la API REST del módulo **AUTH SERVICE**.
                                Este servicio gestiona la autenticación, autorización y
                                administración de roles y usuarios dentro del ecosistema **Red Social**.
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo - Red Social")
                                .email("soporte@redsocial.com")
                                .url("https://redsocial.com"))

                )
                // Agregamos componentes de seguridad
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                // Aplicamos seguridad globalmente (puedes omitir esto si quieres solo algunos endpoints)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
