package com.example.controller;

import com.example.consumer.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月17日 13:06
 */

@RestController
@RequestMapping("consumer")
public class IndexController {

    @Autowired
    private ProviderFeign providerFeign;

    @GetMapping("/index/{msg}")
    public String getMsg(@PathVariable String msg){
        return "我是消费者，正在调用服务方的消息:" + providerFeign.getMsg(msg);
    }

}
