package tech.dtech.athena.config.swagger.provider;

import org.springframework.stereotype.Component;

import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;

@Component(value = "authorizationProvider")
public class AuthorizationHeaderProvider implements IHeaderParameterProvider {

    @Override
    public RequestParameter getParameter() {
        return new RequestParameterBuilder()
            .name("Authorization")
            .in(ParameterType.HEADER)
            .description("Authorization header")
            .required(false)
            .build();
    }

}
