package spring.znevzz.reactive.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.bean.SimpleCacheResponse;

import java.util.HashMap;
import java.util.Map;

@Profile("simple_cache")
@Slf4j
@Component
public class SimpleCacheService implements ICacheService {

    @Autowired
    private DataService service;

    private Map<Object, Object> cache = new HashMap<>();
    @Override
    public Flux<ICacheResponse> get(ICacheRequest request) {
        log.info("get Cache={}", cache.toString());
        String cacheResult = (String) cache.get(request.getPayload());
        ICacheResponse response = new SimpleCacheResponse(cacheResult);
        return Flux.just(response);
    }

    @Override
    public Flux<ICacheResponse> put(ICacheRequest request) {

        service.someAPI(request)
                .subscribe(response -> cache.put(request.getPayload(), response));

        log.info("put Cache={}", cache.toString());

        return Flux.just(
                new SimpleCacheResponse("Fetching"),
                new SimpleCacheResponse(cache.get(request.getPayload()).toString())
        );
    }

    @Override
    public Flux<ICacheResponse> evict(ICacheRequest request) {
        cache.remove(request.getPayload());
        log.info("evict Cache={}", cache.toString());
        return Flux.empty();
    }
}
