package spring.znevzz.reactive.router;

import org.springframework.stereotype.Component;
import spring.znevzz.reactive.constant.Action;
import spring.znevzz.reactive.constant.Request;

@Component
public class CacheMessageRouter {

    public Action routeforRequest(Request request){
        Action result = null;
        if (request == Request.VIEW) {
            result = Action.GET_CACHE;

        } else if (request == Request.GET) {
            result = Action.EVICT_CACHE;
        }
        return result;
    }
}