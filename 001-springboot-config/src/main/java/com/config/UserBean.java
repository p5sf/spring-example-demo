package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author YanZhao
 * @description
 * @date 2022年07月08日 8:06
 */

@Configuration
@PropertySource("classpath:user.properties")
@ConfigurationProperties(prefix = "com.user")
public class UserBean {

    private String userName;

    private String age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
