package spring.znevzz.reactive.bean;

import reactor.core.publisher.Mono;

public interface ICacheRequest<T> {
    String getUser();
    T getPayload();
}
