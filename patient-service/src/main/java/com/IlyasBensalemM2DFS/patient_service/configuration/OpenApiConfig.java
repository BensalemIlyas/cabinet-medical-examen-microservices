package com.IlyasBensalemM2DFS.patient_service.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Patient Service API")
                        .version("1.0")
                        .description("API de gestion des patients")
                        .contact(new Contact()
                                .name("Ilyas Bensalem")
                                .email("ilyasbensalem@gmail.com")))
                .addServersItem(new Server()
                        .url("http://localhost:8081")
                        .description("Serveur local"));
    }

}
