package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.constants.NacosHotConstants;
import com.example.handler.BlockHandler;
import com.example.handler.FallbackHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月17日 22:16
 */

@RestController
public class IndexController {

    private String value;

    private static int count = 0;

    /**
     * 定义接口限流
     * value:资源名称，与Sentinel服务中的资源名设置一致且唯一
     * blockHandler 是处理BlockException的接口名称，接口默认需要和原方法在同一个类中
     * @param name
     * @return
     */

    @GetMapping(value = "/index")
    @SentinelResource(value = "index", blockHandlerClass = BlockHandler.class, blockHandler = "blockHandlerError")
    public String index(@RequestParam(name = "name") String name) {
        return "Hello World: " + name;
    }


    /**
     * 定义降级策略
     * @return
     */
    @GetMapping(value = "/degrade")
    @SentinelResource(value = "testDegradeRule", fallbackClass = FallbackHandler.class, fallback = "fallbackHandlerForDegradeRule")
    public String testDegradeRule() {

        // 保证count为奇数时异常，偶数时不异常
        if ((count++ & 1) == 1) {
            throw new RuntimeException("人为制造异常");
        } else {
            return "降级测试：不发生异常";
        }
    }


    /**
     * 配置热点规则
     * 不用设置@RequestParam，不管是否在url中填写，入参会一直是被访问状态，这样无法测试热点规则
     * @param json1
     * @param json2
     * @return
     */
    @GetMapping(value = "/json")
    @SentinelResource(value = "getJson")
    public String getJson(Boolean json1, Boolean json2) {

        if (json1 != null && json1) {
            return NacosHotConstants.json1;
        }
        if (json2 != null && json2) {
            return NacosHotConstants.json2;
        }
        return NacosHotConstants.json3;
    }


    /**
     * 配置高级热点规则
     * 不用设置@RequestParam，不管是否在url中填写，入参会一直是被访问状态，这样无法测试热点规则
     * @param id
     * @return
     */
    @GetMapping(value = "/html/{id}")
    @SentinelResource(value = "getHtml")
    public String getHtml(@PathVariable int id) {

        if (id <= 0 || id > 3) {
            return NacosHotConstants.errorHtml;
        }
        return NacosHotConstants.html[id - 1];
    }

}
