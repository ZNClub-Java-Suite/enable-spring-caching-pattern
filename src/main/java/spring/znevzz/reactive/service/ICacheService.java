package spring.znevzz.reactive.service;

import reactor.core.publisher.Flux;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;

public interface ICacheService {
    Flux<ICacheResponse> get(ICacheRequest request);

    Flux<ICacheResponse> put(ICacheRequest request);

    Flux<ICacheResponse> evict(ICacheRequest request);
}
