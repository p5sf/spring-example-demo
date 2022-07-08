package com.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月15日 12:25
 */

@RestController
public class IndexController {

    @Value("${testValue}")
    private String open;

    @Value("${testValue}")
    private String testValue;

    @GetMapping
    public String open(){
        return open;
    }

    @GetMapping("test")
    public String value(){
        return testValue;
    }
}
