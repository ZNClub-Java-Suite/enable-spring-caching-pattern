package spring.znevzz.reactive.handler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.constant.Request;
import spring.znevzz.reactive.service.CacheManager;

import java.time.Duration;

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
        return manager.handleCacheRequest(request, Request.VIEW)
                .timeout(Duration.ofSeconds(5))
                .retry(2);
    }
}
