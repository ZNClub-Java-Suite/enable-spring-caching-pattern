package spring.znevzz.reactive.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.bean.SimpleCacheResponse;

import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleCacheService implements ICacheService {

    private DataService service;

    private Map<Object, Object> cache = new HashMap<>();
    @Override
    public Flux<ICacheResponse> get(ICacheRequest request) {
        String cacheResult = (String) cache.get(request.getPayload());
        ICacheResponse response = new SimpleCacheResponse(cacheResult);
        return Flux.just(response);
    }

    @Override
    public Flux<ICacheResponse> put(ICacheRequest request) {

        service.someAPI(request)
                .subscribe(response -> cache.put(request.getPayload(), response));

        return Flux.just(
                new SimpleCacheResponse("Fetching"),
                new SimpleCacheResponse(cache.get(request.getPayload()).toString())
        );
    }

    @Override
    public Flux<ICacheResponse> evict(ICacheRequest request) {
        cache.remove(request.getPayload());
        return Flux.empty();
    }
}
