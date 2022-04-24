package com.lemon.tree.controller;

import com.lemon.tree.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NestedApiCallController {
    @Autowired
    private ApiService service;

    @GetMapping("/anotherApi")
    public ResponseEntity<String> querySample() {
        return service.callAnotherApi();
    }

}
