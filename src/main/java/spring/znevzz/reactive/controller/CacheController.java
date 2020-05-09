package spring.znevzz.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}
