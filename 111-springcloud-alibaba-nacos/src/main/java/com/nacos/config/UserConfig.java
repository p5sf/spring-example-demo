package com.nacos.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月16日 22:23
 */

@RefreshScope
@Data
public class UserConfig {

    @Value("user.username")
    private String username;

    @Value("user.password")
    private String password;
}
