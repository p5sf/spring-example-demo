package com.controller;

import com.config.UserBean;
import com.config.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanZhao
 * @description
 * @date 2022年07月08日 7:46
 */

@RestController
public class IndexController {

    @Autowired
    public UserProperties userProperties;

    @Autowired
    public UserBean userBean;

    @GetMapping
    public String User(){
        return userProperties.getUserName();
    }

    @GetMapping("bean")
    public String show(){
        return userBean.getUserName();
    }
}
