package com.red_social.analysis_service.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI analysisOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Analysis Service - API")
                        .version("1.0")
                        .description("Documentación de la API REST del servicio de análisis de usuarios, estadísticas y reportes.")
                        .contact(new Contact()
                                .name("Equipo de Análisis de Datos")
                                .email("analisis@tuempresa.com"))
                );
    }

}
