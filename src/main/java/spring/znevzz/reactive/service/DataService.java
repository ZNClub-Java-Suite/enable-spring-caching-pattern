package spring.znevzz.reactive.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import spring.znevzz.reactive.bean.ICacheRequest;

@Component
public class DataService {

    public Mono<String> someAPI(ICacheRequest request){
        return Mono.just("{}")
                .log();
    }
}
