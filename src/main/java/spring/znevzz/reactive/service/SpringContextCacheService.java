package spring.znevzz.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;

import java.util.HashMap;
import java.util.Map;

@Profile("context_cache")
@Component
public class SpringContextCacheService implements ICacheService {

    @Autowired
    private DataService service;

    private Map<Object, Object> responses = new HashMap<>();

    @Cacheable("request")
    @Override
    public Flux<ICacheResponse> get(ICacheRequest request) {

        Mono<Mono<String>> callable = Mono.fromCallable(() -> service.someAPI(request));
        Mono<Mono<String>> result = callable.subscribeOn(Schedulers.boundedElastic());
//        return Flux.just(new SimpleCacheResponse(result.flatMap(String::toString)));
        return Flux.empty();
    }

    @Override
    public Flux<ICacheResponse> put(ICacheRequest request) {
        return null;
    }

    @Override
    public Flux<ICacheResponse> evict(ICacheRequest request) {
        return null;
    }
}
