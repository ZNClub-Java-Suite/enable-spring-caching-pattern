package spring.znevzz.reactive.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.bean.SimpleCacheRequest;
import spring.znevzz.reactive.handler.IRequestHandler;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class CacheController {

    private final IRequestHandler handler;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @PostMapping("/cache")
    public Flux<ICacheResponse> fromCache(@RequestBody SimpleCacheRequest request) {
        Flux<ICacheResponse> firstFetch = handler.viewFromCache(request).log("viewFromCache");
        Flux firstResult = firstFetch
                .map(ICacheResponse::getPayload)
                .filter(Optional::isPresent)
                .log("result of viewFromCache");

        firstFetch
                .switchIfEmpty(handler.getFromCache(request))
                .log("secondFetch");

        return firstResult;
    }

}
