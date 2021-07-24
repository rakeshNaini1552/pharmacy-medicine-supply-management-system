package com.pod2.portal;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@SpringBootApplication
@EnableSwagger2
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Bean
    public Docket configureSwagger() {
        return new Docket(DocumentationType.SWAGGER_2).select()
//                .paths(PathSelectors.ant("/user/*"))
                .apis(RequestHandlerSelectors.basePackage("com.pod2.portal.controller")).build()
                .apiInfo(apiInfo());
    }
	private ApiInfo apiInfo() {
        return new ApiInfo("USers Api", "Sample API for users", "1.0", "path/to/terms",
                new Contact("Rakesh", "http://www.google.com", "rakeshmudhiraj155@gmail.com"), "API License",
                "http://www.google.com", Collections.emptyList());
    }
}
