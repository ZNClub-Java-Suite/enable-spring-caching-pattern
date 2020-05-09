package spring.znevzz.reactive.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import spring.znevzz.reactive.bean.ICacheRequest;
import spring.znevzz.reactive.bean.ICacheResponse;
import spring.znevzz.reactive.constant.Action;
import spring.znevzz.reactive.constant.Request;
import spring.znevzz.reactive.router.CacheMessageRouter;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Component
public class CacheManager {

    private CacheMessageRouter router;
    private ICacheService cacheService;
    private Map<ICacheRequest, Optional<ICacheResponse>> responses;

    public Optional<ICacheResponse> handleCacheRequest(ICacheRequest request, Request view) {

        Action action = router.routeforRequest(view);

        delegateAction(request, action);

        return responses.get(request);
    }

    private void delegateAction(ICacheRequest request, Action action) {
        if (action == Action.GET_CACHE) {
            responses.put(request, cacheService.get(request).toStream(1).findFirst());

        } else if (action == Action.EVICT_CACHE) {
            responses.put(request, cacheService.evict(request).toStream(1).findFirst());

        } else if (action == Action.PUT_CACHE) {
            responses.put(request, cacheService.put(request).toStream(1).findFirst());
        }
    }
}
