package com.redis.service;

import com.redis.constant.RedisKeyConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author YanZhao
 * @description:获取关注信息
 * @date 2022年06月03日 15:30
 */

@Service
public class FollowService {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 分页查询
     * @Parem: [UserId]
     * @return: java.util.Set<java.lang.Object>
     */
    public Set<Object> followByPages(Long UserId){
        Long start = 1L;
        Long end = 20L;
        String key = RedisKeyConstant.followers.getKey() + UserId;
        Set range = redisTemplate.opsForZSet().reverseRange(key, start, end);
        return range;
    }


    /**
     * 查询所有关注
     * @Parem: [userid]
     * @return: void
     */
    public Set allFollow(Long userid) {
        String key = RedisKeyConstant.following.getKey() + userid;
        return redisTemplate.opsForSet().members(key);
    }

    /***
     * type 1关注 0 取关
     * @Parem: [userId, followId, type]
     * @return: void
     */
    public void addFollow(Long userId, Long followId, int type) {
       // TODO：查询用户信息
        String key = RedisKeyConstant.followers.getKey() + userId;
        if (type == 0) {
            redisTemplate.opsForSet().remove(key, followId);
        }else {
            redisTemplate.opsForSet().add(key, followId);
        }
    }
}
