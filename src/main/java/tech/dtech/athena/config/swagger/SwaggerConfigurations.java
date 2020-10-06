package tech.dtech.athena.config.swagger;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import tech.dtech.athena.AthenaApplication;
import tech.dtech.athena.config.swagger.provider.IRequestParameterProvider;
import tech.dtech.athena.model.Account;

@Configuration
public class SwaggerConfigurations {

    @Autowired
    @Qualifier("authorizationProvider")
    private IRequestParameterProvider authorizationHeader;

    @Bean
    public Docket athenaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(AthenaApplication.class.getPackageName()))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(Account.class)
                .globalRequestParameters(Arrays.asList(authorizationHeader.getParameter()));
    }

}
