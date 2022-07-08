package com.redis.service;

import com.redis.constant.RedisKeyConstant;
import com.redis.dto.RankDto;
import com.redis.utils.RedisUtils;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月03日 17:16
 */

@Service
public class RankService {


    private RedisUtils redisUtils;


    private List<RankDto> buildRedisRankToBizDO(Set<ZSetOperations.TypedTuple<Object>> result, long offset) {
        List<RankDto> rankList = new ArrayList<>(result.size());
        long rank = offset;
        for (ZSetOperations.TypedTuple<Object> sub : result) {
            rankList.add(new RankDto(rank++, Math.abs(sub.getScore().floatValue()), Long.parseLong(sub.toString())));
        }
        return rankList;
    }


    /**
     * 获取获取排名前几的数据
     * @Parem: [n]
     * @return: java.util.List<com.redis.dto.RankDto>
     */

    public List<RankDto> getTop(int n) {
        Set<ZSetOperations.TypedTuple<Object>> result = redisUtils.rangeWithScore(RedisKeyConstant.global_rank.getKey(), 0, n);
        return buildRedisRankToBizDO(result, 1);
    }

    /**
     * 获取用户排名位置
     * @Parem: [userId]
     * @return: com.redis.dto.RankDto
     */
    public RankDto getRank(Long userId){
        // 获取排名，没有排名直接返回一个默认的
        Long rank = redisUtils.rank(RedisKeyConstant.global_rank.getKey(), String.valueOf(userId));
        if (rank == null) {
            return new RankDto(-1L, 0F, userId);
        }
        // 获取积分
        Double score = redisUtils.score(RedisKeyConstant.global_rank.getKey(), String.valueOf(userId));
        return new RankDto(rank + 1, Math.abs(score.floatValue()), userId);
    }

    /**
     * 获取用户所在排行榜位置，以及排行中其前后的位置
     * @Parem: [userId, n]
     * @return: java.util.List<com.redis.dto.RankDto>
     */
    public List<RankDto> getRankAroundUser(Long userId, int n) {
        RankDto rank = getRank(userId);
        // FIXME:如果用户没有上榜，则不返回
        if (rank.getRank() <= 0) {
            return Collections.emptyList();
        }
        Set<ZSetOperations.TypedTuple<Object>> result = redisUtils.rangeWithScore(RedisKeyConstant.global_rank.getKey(), Math.max(0, rank.getRank() - n - 1), rank.getRank() + n - 1);
        return buildRedisRankToBizDO(result, rank.getRank() - n);

    }







    /**
     * 更新用户积分，并获取最新个人所在排行榜信息
     * @Parem: [userId, score]
     * @return: com.redis.dto.RankDto
     */
    public RankDto updateRank(Long userId,Float score){
        redisUtils.add(RedisKeyConstant.global_rank.getKey(), String.valueOf(userId), -score);
        Long rank = redisUtils.rank(RedisKeyConstant.global_rank.getKey(), String.valueOf(userId));
        return new RankDto(rank + 1, score, userId);
    }
}
