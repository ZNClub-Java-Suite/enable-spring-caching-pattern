package spring.znevzz.reactive.handler;

import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public interface  IRequestHandler<T,R> extends HandlerFunction {

    Mono<R> get(T t);

    Flux<R> getFromCache(T t);

    default <T extends ServerResponse> Mono<T> welcome(ServerRequest serverRequest) {
        Mono welcomeMessage = Mono.empty();
        Mono<String> responseMessage = Mono.just("welcome");
        welcomeMessage = ok()
                .hint("key", "value")
                .body(responseMessage, String.class)
                ;
        return welcomeMessage;
    }
}
