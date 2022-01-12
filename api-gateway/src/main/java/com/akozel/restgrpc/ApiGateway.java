package com.akozel.restgrpc;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Todo List API Gateway",
        version = "0.0",
        description = "My API",
        contact = @Contact(url = "https://kaseya.com", name = "Andrey", email = "andrey.kozel@kaseya.com")
    )
)
public class ApiGateway {

  public static void main(final String[] args) {
    Micronaut.run(ApiGateway.class);
  }

}
