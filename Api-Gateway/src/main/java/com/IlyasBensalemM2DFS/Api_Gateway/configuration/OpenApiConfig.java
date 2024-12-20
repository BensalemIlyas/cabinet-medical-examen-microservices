package com.IlyasBensalemM2DFS.Api_Gateway.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway cabinet medical")
                        .version("1.0")
                        .description("API Gateway cabinet medical")
                        .contact(new Contact()
                                .name("Ilyas Bensalem")
                                .email("ilyasbensalem@gmail.com")))
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Serveur local"));
    }
}
