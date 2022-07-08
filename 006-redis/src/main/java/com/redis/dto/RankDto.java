package com.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月03日 17:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankDto implements Serializable {
    private static final long serialVersionUID = 4804922606006935590L;

    
    /**
     * 排名
     * @Parem: 
     * @return: 
     */
    private Long rank;
    
    /**
     * 积分
     * @Parem: 
     * @return: 
     */
    private Float score;

    /**
     * 用户ID
     * @Parem:
     * @return:
     */
    private Long userId;
}
