package spring.znevzz.reactive.router;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import spring.znevzz.reactive.handler.IRequestHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * more information at:
 * - https://spring.io/blog/2016/09/22/new-in-spring-5-functional-web-framework
 * - https://dzone.com/articles/creating-multiple-routerfunctions-in-spring-webflux
 */

/*
@Configuration
public class CacheRouter {
    @Bean
    public RouterFunction<ServerResponse> routers(IRequestHandler handler) {
        return route(
                GET("/")
                        .and(accept(APPLICATION_JSON)),
                handler::welcome)
                .andRoute(
                        GET("/cache/")
                                .and(accept(APPLICATION_JSON)),
                        handler.get("demo"))
                ;
    }
}*/
