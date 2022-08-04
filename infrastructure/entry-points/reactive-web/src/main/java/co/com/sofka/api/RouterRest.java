package co.com.sofka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {
    private static final String ROUTE_PET = "/api/pet/";
@Bean
public RouterFunction<ServerResponse> routerFunction(Handler handler) {

    return nest(path(ROUTE_PET),
            route(POST(""), handler::createPet)
            .andRoute(GET(""), handler::getAllPet )
            .andRoute(GET("{idPet}"), handler::getPet)
            .andRoute(PUT("{idPet}"), handler::updatePet));


    }
}
