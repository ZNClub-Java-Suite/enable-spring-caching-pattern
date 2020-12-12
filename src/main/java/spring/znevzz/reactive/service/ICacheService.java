package spring.znevzz.reactive.service;

import reactor.core.publisher.Flux;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.exception.NoResponseException;

public interface ICacheService {
    Flux<ICacheResponse> get(ICacheRequest request);

    Flux<ICacheResponse> put(ICacheRequest request) throws NoResponseException;

    Flux<ICacheResponse> evict(ICacheRequest request);
}
