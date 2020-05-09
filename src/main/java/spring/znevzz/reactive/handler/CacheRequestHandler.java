package spring.znevzz.reactive.handler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.bean.SimpleCacheResponse;
import spring.znevzz.reactive.constant.Request;
import spring.znevzz.reactive.service.CacheManager;

@AllArgsConstructor
@Component
public class CacheRequestHandler implements IRequestHandler<ICacheRequest, ICacheResponse> {

    private CacheManager manager;

    @Override
    public Mono handle(ServerRequest serverRequest) {
        return Mono.empty();
    }

    @Override
    public Mono<ICacheResponse> get(ICacheRequest iCacheRequest) {
        return Mono.empty();
    }

    @Override
    public Flux<ICacheResponse> getFromCache(ICacheRequest request) {
        return Flux.just(
                manager.handleCacheRequest(request, Request.VIEW)
                        .orElse(new SimpleCacheResponse("Missing"))
        );
    }
}
