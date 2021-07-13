package com.experiment.authorize.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEME_NAME = "Bearer OAuth2 Token";

    @Bean
    public OpenAPI openApiConfiguration() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(
                        new Components()
                                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                        new SecurityScheme()
                                                .name(SECURITY_SCHEME_NAME)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(new Info()
                        .title("Authorize REST Service Documentation")
                        .version("1.0.0")
                        .description("Auto generated documentation for Authorize rest services")
                        .termsOfService("Terms of service")
                        .license(getLicense())
                        .contact(getContact()));
    }

    public Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail("can.sahintas2324@gmail.com");
        contact.setName("Can Şahintaş");
        contact.setUrl("https://github.com/crymnc");
        contact.setExtensions(Collections.emptyMap());
        return contact;
    }

    public License getLicense() {
        License license = new License();
        license.setName("Apache License, Version 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
        license.setExtensions(Collections.emptyMap());
        return license;
    }
}
