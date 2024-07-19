package com.awaion.cloud.mygateway;

import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GatewayController {
    @Resource
    private GatewayProperties properties;
    @Resource
    private DiscoveryLocatorProperties discoveryLocatorProperties;

    @GetMapping("/getRouter")
    private List<RouteDefinition> getRouter() {
        // http://localhost:10007/getRouter
        return properties.getRoutes();
    }

    @GetMapping("/getDiscovery")
    private DiscoveryLocatorProperties getDiscoveryLocatorProperties() {
        // http://localhost:10007/getDiscovery
        return discoveryLocatorProperties;
    }
}
