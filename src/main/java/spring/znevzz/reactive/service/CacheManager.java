package spring.znevzz.reactive.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.constant.Action;
import spring.znevzz.reactive.constant.Request;
import spring.znevzz.reactive.router.CacheMessageRouter;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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

        log.info("Action={}, Response={}", action.name(), responses.toString());

        return responses.get(request);
    }

    private void delegateAction(ICacheRequest request, Action action) {
        if (action == Action.GET_CACHE) {
            responses.put(request,
                    cacheService.get(request)
            );

        } else if (action == Action.EVICT_CACHE) {
            cacheService.evict(request);
            responses.put(request,
                    cacheService.put(request)
            );

        } else if (action == Action.PUT_CACHE) {
            responses.put(request,
                    cacheService.put(request)
            );
        }
    }
}
