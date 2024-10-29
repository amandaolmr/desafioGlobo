package com.globo.desafioGlobo.Produto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio Globo API")
                        .description("API para o Desafio Globo que realiza operações CRUD em uma entidade Produto")
                        .version("1.0"));
    }
}