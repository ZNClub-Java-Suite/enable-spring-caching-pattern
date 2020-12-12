package spring.znevzz.reactive.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.bean.SimpleCacheResponse;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SimpleCacheService implements ICacheService {

    @Autowired
    @Setter
    private IDataService<Mono<String>, ICacheRequest> service;
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

        service.generate(request)
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
