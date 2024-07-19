package com.awaion.demo028.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {
    @PreAuthorize("hasAnyAuthority('permission1')")
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello rest");
    }

    @PreAuthorize("hasAnyAuthority('permission2')")
    @GetMapping("/world")
    public Mono<String> world() {
        return Mono.just("world rest");
    }
}

