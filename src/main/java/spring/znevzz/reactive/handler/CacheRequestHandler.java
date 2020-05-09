package spring.znevzz.reactive.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;

@Component
public class CacheRequestHandler implements IRequestHandler<ICacheRequest, ICacheResponse> {

    @Override
    public Flux<ICacheResponse> get(ICacheRequest iCacheRequest) {
        return Flux.empty();
    }

    @Override
    public Mono handle(ServerRequest serverRequest) {
        return Mono.empty();
    }
}
