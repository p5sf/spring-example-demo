package com.example.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author YanZhao
 * @description:服务提供者
 * @date 2022年06月17日 13:04
 */

@FeignClient("nacos-example-provider")
public interface ProviderFeign {

    @GetMapping("/provider/{msg}")
    String getMsg(@PathVariable("msg") String msg);
}
