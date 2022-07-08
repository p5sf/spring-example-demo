package com.redis.controller;

import com.redis.service.FollowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author YanZhao
 * @description：获取用户关注信息
 * @date 2022年06月03日 15:27
 */

@RestController
@RequestMapping("gather/follow")
public class FollowController {

    @Resource
    private FollowService fwService;

    /***
     * 获取用户信息关注分页列表
     * @Parem: [userId]
     * @return: void
     */
    @GetMapping("index")
    public void FollowBy(Long userId) {
        Set<Object> followByPages = fwService.followByPages(userId);
        System.out.println(followByPages);
    }

    public void allFollows(Long userid) {
        fwService.allFollow(userid);
    }

    /**
     * 关注 1 取消关注0
     * @Parem:
     * @return:
     */
    public void addFollowFeed(String userId,String followId,int type){

    }
}
