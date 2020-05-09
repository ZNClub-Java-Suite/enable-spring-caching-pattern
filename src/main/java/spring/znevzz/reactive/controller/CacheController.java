package spring.znevzz.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.bean.SimpleCacheRequest;

@RestController
public class CacheController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @PostMapping("/cache")
    public Flux<ICacheResponse> fromCache(@RequestBody SimpleCacheRequest request) {
        return Flux.empty();
    }

}
