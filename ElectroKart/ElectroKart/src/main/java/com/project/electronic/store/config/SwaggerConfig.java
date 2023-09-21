package com.project.electronic.store.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Electro Kart API",
                description = "This is backend of electronic store project API developed by Prakhar Srivastava",
                version = "1.0V",
                contact = @Contact(
                        name = "Prakhar Srivastava",
                        email = "prakharsrivastava9786@gmail.com",
                        url = "prakhar.com"
                ),
                license = @License(
                        name = "OPEN License",
                        url = "prakhar.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "This is external docs",
                url = "prakhar.com"
        )
)
public class SwaggerConfig {



    /* @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Electro Kart API")
                        .description("This is electronic store project API developed by Prakhar Srivastava")
                        .version("1.0")
                        .contact(new Contact().name("Prakhar Srivastava").email("prakharsrivastava9786@gmail.com").url("prakhar.com"))
                        .license(new License().name("Apache"))

                ).externalDocs(new ExternalDocumentation().url("prakhar.com").description("this is external url"));

    } */

}
