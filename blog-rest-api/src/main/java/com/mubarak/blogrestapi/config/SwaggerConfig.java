package com.mubarak.blogrestapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Blog Rest Apis",
                "Spring boot blog rest api documentation",
                "Version 1",
                "Terms of Services",
                new Contact("Blog App", "localhost.com","blogapp@email.com"),
                "Licence of API",
                "www.license.com",
                Collections.emptyList()
        );
    }


    @Bean
    public Docket api(){
     return new Docket(DocumentationType.SWAGGER_2)
             .apiInfo(apiInfo())
             .select()
             .apis(RequestHandlerSelectors.any())
             .paths(PathSelectors.any())
             .build();
    }
}
