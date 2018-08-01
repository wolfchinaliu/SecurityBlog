/*
package com.liuzihu.blog.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

*/
/**
 * @author Jerry
 * @version 1.0
 * @Description
 * @Date Create by 2018-06-01 10:47
 * @Modified by
 *//*

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liuzihu.blog.user"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .securitySchemes(newArrayList(apiKey()))
                */
/*.securityContexts(newArrayList(securityContext()))*//*
;
    }
    @Bean
    public Docket test() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liuzihu.blog.test"))
                .paths(PathSelectors.ant("/test/**"))
                .build()
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(newArrayList(securityContext()));
    }

    @Bean
    public Docket token(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("token")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liuzihu.blog.security"))
                .paths(PathSelectors.ant("/token/*"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("token", "Authorization", "header");
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("token",authorizationScopes));
    }
}
*/
