package spring.znevzz.reactive.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.constant.Action;
import spring.znevzz.reactive.constant.Request;
import spring.znevzz.reactive.router.CacheMessageRouter;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class CacheManager {
    @Autowired
    @Setter
    private CacheMessageRouter router;
    @Autowired
    @Setter
    private ICacheService cacheService;
    private Map<ICacheRequest, Flux<ICacheResponse>> responses = new HashMap<>();

    public Flux<ICacheResponse> handleCacheRequest(ICacheRequest request, Request view) {

        Action action = router.routeforRequest(view);

        delegateAction(request, action);

        return responses.get(request);
    }

    private void delegateAction(ICacheRequest request, Action action) {
        if (action == Action.GET_CACHE) {
            responses.put(request,
                    cacheService.get(request).timeout(Duration.ofSeconds(1))
            );

        } else if (action == Action.EVICT_CACHE) {
            responses.put(request,
                    cacheService.evict(request).timeout(Duration.ofSeconds(1))
            );

        } else if (action == Action.PUT_CACHE) {
            responses.put(request,
                    cacheService.put(request).timeout(Duration.ofSeconds(1))
            );
        }
    }
}
