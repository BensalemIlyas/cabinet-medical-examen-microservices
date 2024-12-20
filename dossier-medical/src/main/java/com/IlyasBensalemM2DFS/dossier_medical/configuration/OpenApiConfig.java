package com.IlyasBensalemM2DFS.dossier_medical.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dossier Medical Service API")
                        .version("1.0")
                        .description("API de gestion des dossiers médicaux")
                        .contact(new Contact()
                                .name("Ilyas Bensalem")
                                .email("ilyasbensalem@gmail.com")))
                .addServersItem(new Server()
                        .url("http://localhost:8084")
                        .description("Serveur local"));
    }
}
