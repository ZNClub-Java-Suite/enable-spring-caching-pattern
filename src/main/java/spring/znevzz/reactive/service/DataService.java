package spring.znevzz.reactive.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.znevzz.reactive.bean.ICacheRequest;

import java.time.Duration;

public class DataService {

    public Mono<String> someAPI(ICacheRequest request){
        return Mono.just("{}")
                .log();
    }
}
