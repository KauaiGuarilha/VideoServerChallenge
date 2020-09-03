package com.videoserverchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean // http://localhost:8080/swagger-ui.html#/
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.videoserverchallenge.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("Kauai Guarilha", "https://www.linkedin.com/in/kauai-guarilha/", "kauai.guarilha@hotmail.com"))
                .title("Video Server Challege")
                .description("Project Documentation")
                .license("Apache Licence Version 2.0")
                .licenseUrl("https://apache.org")
                .version("1.0")
                .build();
    }

    private List<ResponseMessage> responseMessageForGET() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(200)
                    .message("Ok")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(201)
                    .message("Created")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Bad Request")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Internal Server Error")
                    .responseModel(new ModelRef("Error"))
                    .build());
        }};
}}
