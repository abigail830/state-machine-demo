package com.github.abigail830.statemachinedemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<>();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.abigail830.timeticket.api"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .enable(enableSwagger);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Time ticket backend")
                .description("Time ticket wechat mini app backend")
                .contact(new Contact("SaraQian", null, "abigail830@163.com"))
                .version("0.0.1")// 版本号
                .build();
    }
}