package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author YanZhao
 * @description
 * @date 2022年07月08日 7:41
 */

@Component
public class UserProperties {

  @Value("${com.user.userName}")
    private String userName;

    @Value("${com.user.age}")
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
