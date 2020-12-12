package spring.znevzz.reactive.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import spring.znevzz.reactive.bean.SimpleCacheRequest;
import spring.znevzz.reactive.handler.CacheRequestHandler;
import spring.znevzz.reactive.handler.IRequestHandler;
import spring.znevzz.reactive.router.CacheMessageRouter;
import spring.znevzz.reactive.service.CacheManager;
import spring.znevzz.reactive.service.MockDataService;
import spring.znevzz.reactive.service.SimpleCacheService;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CacheController.class)
class CacheControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CacheRequestHandler handler;

    @BeforeEach
    void setUp() {

    }

    @Test
    void welcome() {
        webTestClient.get()
                .uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void getFromCache() {
        SimpleCacheRequest request = new SimpleCacheRequest();
        request.setUser("test");
        request.setPayload("{}");
        webTestClient.post()
                .uri("/cache")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }


    @Test
    void getFromCacheCheckResponse() {
        SimpleCacheRequest request = new SimpleCacheRequest();
        request.setUser("test");
        request.setPayload("{}");
        webTestClient.post()
                .uri("/cache")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("[{}]");
    }
    @TestConfiguration
    static class TestConfig {
        @Bean
        public IRequestHandler handler(){
            CacheManager manager = new CacheManager();
            SimpleCacheService cacheService = new SimpleCacheService();
            cacheService.setService(new MockDataService());
            manager.setCacheService(cacheService);
            manager.setRouter(new CacheMessageRouter());
            return new CacheRequestHandler(manager);
        }
    }
}