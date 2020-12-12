package spring.znevzz.reactive.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import spring.znevzz.reactive.bean.ICacheRequest;

@Component
public class MockDataService implements IDataService<Mono<String>, ICacheRequest>{

    public Mono<String> someAPI(ICacheRequest request){
        return Mono.just("{}")
                .log();
    }

    @Override
    public Mono<String> generate(ICacheRequest request) {
        return someAPI(request);
    }
}
