package com.redis.service;

import com.redis.constant.RedisKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月01日 15:21
 */


@Service
public class LoginService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /***
     * 发送验证码
     * @Parem: [username, password, verifyCoder]
     * @return: java.lang.String
     */
    public String sendCode(String phone) {
        // 根据手机号获取验证码
        String code = getCodeByPhone(phone);
        Assert.notNull(code, "验证码不能为空，请重新发送");
        return code;
    }


    public String send(String phone) {
        Assert.notNull(phone, "手机号不能为空");
        // 随机生成6位数
        final String randomCode = String.valueOf((Math.random() * 9 + 1) * 100000).substring(0,6);
        // 如果已经生成则直接返回
       String code =  getCodeByPhone(phone);
        if (getCodeByPhone(phone) != null) {
            return code;
        }
        String key = RedisKeyConstant.verify_code.getKey() + phone;
        redisTemplate.opsForValue().set(key, randomCode, 60, TimeUnit.SECONDS);
        return randomCode;

    }


    /***
     * 获取验证码
     * @Parem: [phone]
     * @return: java.lang.String
     */
    public String getCodeByPhone(String phone) {
        String key = RedisKeyConstant.verify_code.getKey() + phone;
        return redisTemplate.opsForValue().get(key);
    }
}
