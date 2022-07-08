package com.redis.controller;

import com.redis.dto.RankDto;
import com.redis.service.RankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YanZhao
 * @description:实现排行榜
 * @date 2022年06月03日 17:14
 */

@RestController
@RequestMapping("gather/rank")
public class RankController {

    @Resource
    private RankService rankService;


    @GetMapping("top")
    public List<RankDto> showTop(int n) {
        return rankService.getTop(n);
    }


    @GetMapping(path = "/rank")
    public RankDto queryRank(long userId) {
        return rankService.getRank(userId);
    }

    @GetMapping(path = "/around")
    public List<RankDto> around(long userId, int n) {
        return rankService.getRankAroundUser(userId, n);
    }
}
