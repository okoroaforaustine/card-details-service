/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.config;

import com.cardscheme.details.Utils.AppUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author austine.okoroafor
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
     @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cardscheme.details.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(securityHeader())
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "cardscheme - detials",
                "Card scheme",
                "1.0",
                "",
                new Contact("Austine Okoroafor", "", "Okoroaforaustine@gmail.com"), "License of API", "API license URL", Collections.emptyList());
    }

    private List<Parameter> securityHeader() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization")                // name of header
                .modelRef(new ModelRef("string"))
                .parameterType("header")               // type - header
                .defaultValue("test")        // based64 of - zone:mypassword
                .required(true)                // for compulsory
                .build();


        ParameterBuilder aParameterBuilder2 = new ParameterBuilder();
        aParameterBuilder2.name("Signature")                // name of header
                .modelRef(new ModelRef("string"))
                .parameterType("header")               // type - header
                .defaultValue("ac5efa5e16ff7bacb6a086415725352a941b40556eee46f0a03c9302e33f3999")        // based64 of - zone:mypassword
                .required(true)                // for compulsory
                .build();

        ParameterBuilder aParameterBuilder3 = new ParameterBuilder();
        aParameterBuilder3.name("Timestamp")                // name of header
                .modelRef(new ModelRef("string"))
                .parameterType("header")               // type - header
                .defaultValue("" + AppUtils.getCurrentTime())        // based64 of - zone:mypassword
                .required(true)                // for compulsory
                .build();

        List<Parameter> aParameters = new ArrayList<>();


        aParameters.add(aParameterBuilder.build());
        aParameters.add(aParameterBuilder2.build());
        aParameters.add(aParameterBuilder3.build());

        return aParameters;
    }
    
}
