package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanZhao
 * @description: 服务提供者
 * @date 2022年06月17日 12:59
 */

@RestController
@RequestMapping("/provider")
public class ProviderController {


    @GetMapping("/{msg}")
    public String getMsg(@PathVariable("msg") String message) {
        return "我是消息生产者-" + message;
    }


}
