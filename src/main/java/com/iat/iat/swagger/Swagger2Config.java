package com.iat.iat.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2Config extends WebMvcConfigurationSupport {
    @Bean
    public Docket QCiApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iat.iat"))
                .paths(PathSelectors.any())//PathSelectors.ant("/api/*")
                .build()
                .apiInfo(apiInfo());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IAT API")
                .description("IAT APPLICATION DOCUMENTATION DEVICE")
                .termsOfServiceUrl("https://www.iatplatform.com")
                .contact(new Contact("GIGG GLOBAL DIGITAL SOLUTIONS LIMITED","https://www.iatplatform.com",
                        "info@iatplatform.com"))
                .license("A license given")
                .licenseUrl("https://www.iatplatform.com")
                .version("1.0")
                .build();
    }

}
