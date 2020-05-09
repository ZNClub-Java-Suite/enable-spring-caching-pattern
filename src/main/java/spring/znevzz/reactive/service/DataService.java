package spring.znevzz.reactive.service;

import reactor.core.publisher.Flux;
import spring.znevzz.reactive.bean.ICacheRequest;

import java.time.Duration;

public class DataService {

    public Flux<String> someAPI(ICacheRequest request){
        return Flux.just("{}")
                .delayElements(Duration.ofSeconds(10))
                .log();
    }
}
