package com.awaken.tool.demo005.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@RestController
//@Controller
@Tag(name = "请求转发/重定向/调用")
public class Demo005Controller {
    private final RestTemplate restTemplate;

    Demo005Controller() {
        restTemplate = new RestTemplate();
    }

//    @RequestMapping(value = "/api/geeker/login")
//    @Operation(summary = "请求转发")
//    public void api1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String requestUrl = request.getRequestURI().toString();
//        System.out.println(requestUrl);
//        requestUrl = requestUrl.substring(4);
//        System.out.println(requestUrl);
//        String url = "https://mock.mengxuegu.com/mock/629d727e6163854a32e8307e" + requestUrl;
////        String url = "http://43.159.128.146/page-list";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//        dispatcher.forward(request, response);
//    }

//    @RequestMapping(value = "/api/geeker/login")
//    @Operation(summary = "请求重定向")
//    public void api2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String requestUrl = request.getRequestURI().toString();
//        System.out.println(requestUrl);
//        requestUrl = requestUrl.substring(4);
//        System.out.println(requestUrl);
//        String url = "https://mock.mengxuegu.com/mock/629d727e6163854a32e8307e" + requestUrl;
////        String url = "http://43.159.128.146/page-list";
//        response.sendRedirect(url);
//    }

    @PostMapping(value = "/api/**")
    @Operation(summary = "内部调用")
    public ResponseEntity<?> api3(@RequestBody Map body, HttpServletRequest request) throws ServletException, IOException {
        String requestUrl = request.getRequestURI().toString();
        requestUrl = requestUrl.substring(4);
        String url = "https://mock.mengxuegu.com/mock/629d727e6163854a32e8307e" + requestUrl;

        // 转换原始请求信息为HttpEntity
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.add(headerName, request.getHeader(headerName));
        }

        HttpEntity<?> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.resolve(request.getMethod()), entity, Object.class);
        return response;
    }

    @GetMapping(value = "/api/**")
    @Operation(summary = "内部调用")
    public ResponseEntity<?> api4(Map body, HttpServletRequest request) throws ServletException, IOException {
        String requestUrl = request.getRequestURI().toString();
        requestUrl = requestUrl.substring(4);
        String url = "https://mock.mengxuegu.com/mock/629d727e6163854a32e8307e" + requestUrl;

        // 转换原始请求信息为HttpEntity
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.add(headerName, request.getHeader(headerName));
        }

        HttpEntity<?> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.resolve(request.getMethod()), entity, Object.class);
        return response;
    }


}
