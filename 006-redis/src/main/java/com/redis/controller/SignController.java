package com.redis.controller;

import com.redis.service.SignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author YanZhao
 * @description 获取用户签到
 * @date 2022年06月01日 20:22
 */

@RestController
@RequestMapping("sign")
public class SignController {

    @Resource
    private SignService signService;


    /***
     * 获取用户签到情况，默认当月
     * @Parem: [userid, dateStr]
     * @return: java.util.Map<java.lang.String, java.lang.Boolean>
     */
    @GetMapping("")
    public Map<String, Boolean> getSignInfo(Long userid, String dateStr) {
        return signService.getSignInfo(userid, dateStr);
    }

    /***
     * 获取用户签到次数
     * @Parem:
     * @return:
     */
    @GetMapping("count")
    public Long getSignCount(Long userId, String dateStr) {
        return signService.getSignCount(userId, dateStr);
    }

    /***
     * 查看用户是否签到
     * @Parem: []
     * @return: java.lang.Boolean
     */
    public Boolean isSign(Long userId, String dateStr) {
        return signService.isSign(userId, dateStr);
    }
}
