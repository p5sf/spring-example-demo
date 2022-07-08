package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanZhao
 * @description
 * @date 2022年07月08日 7:34
 */

@RestController
public class IndexController {

    @GetMapping("")
    public String Index() {
        return "Hello World";
    }
}
