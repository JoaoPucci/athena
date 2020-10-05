package tech.dtech.athena.config.swagger.provider;

import springfox.documentation.service.RequestParameter;

public interface IHeaderParameterProvider {
    RequestParameter getParameter();
}
