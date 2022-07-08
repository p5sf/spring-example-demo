package com.redis.controller;

import com.redis.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月01日 15:20
 */

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    /***
     * 发送验证码
     * @Parem: [username, password, phone, verifyCoder]
     * @return: java.lang.String
     */
    @RequestMapping("/sendCode")
    public String register(String phone) {
        return loginService.send(phone);
    }
}
