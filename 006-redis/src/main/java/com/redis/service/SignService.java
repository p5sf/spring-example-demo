package com.redis.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月01日 20:23
 */

@Service
public class SignService {

    @Resource
    private RedisTemplate redisTemplate;


    /***
     * 获取当月签到
     * @Parem: [UserId, dateStr]
     * @return: java.util.Map<java.lang.String, java.lang.Boolean>
     */
    public Map<String, Boolean> getSignInfo(Long UserId, String dateStr) {
        // TODO：获取用户信息
        // 获取日期
        Date date = getDate(dateStr);
        // 构建 key
        String signKey = buildSignKey(UserId, date);


        return null;
    }

    /***
     * 获取用户签到次数
     * @Parem: [userId, dateStr]
     * @return: java.lang.Long
     */
    public Long getSignCount(Long userId, String dateStr) {
        // TODO：获取用户信息
        Date date = getDate(dateStr);
        String signKey = buildSignKey(userId, date);
        return (Long) redisTemplate.execute((RedisCallback) con -> con.bitCount(signKey.getBytes(StandardCharsets.UTF_8)));
    }

    /***
     * 用户签到
     * @Parem:
     * @return:
     */
    public void sign(Long userid, String dateStr) {
        Date date = getDate(dateStr);
        String signKey = buildSignKey(userid, date);
        int offset = DateUtil.dayOfMonth(date) - 1;
        if (!isSign(userid, dateStr)) {
            // 签到
            redisTemplate.opsForValue().setBit(signKey, offset, true);
        }
    }

    /***
     * 查看用户是否签到
     * @Parem: [userId, dateStr]
     * @return: java.lang.Boolean
     */
    public Boolean isSign(Long userId, String dateStr) {
        // TODO：获取用户信息
        Date date = getDate(dateStr);
        String signKey = buildSignKey(userId, date);
        int offset = DateUtil.dayOfMonth(date) - 1;
        return redisTemplate.opsForValue().getBit(signKey, offset);
    }

    /***
     * 构建 key
     * @Parem: [userId, date]
     * @return: java.lang.String
     */
    private String buildSignKey(Long userId, Date date) {
        return String.format("user:sign:%d:%s", userId, DateUtil.format(date, "yyyyMM"));
    }

    /**
     * 获取日期
     *
     * @param dateStr
     * @return
     */
    private Date getDate(String dateStr) {
        if (StrUtil.isBlank(dateStr)) {
            return new Date();
        }
        try {
            return DateUtil.parseDate(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("请传入yyyy-MM-dd的日期格式");
        }
    }


    /**
     * TODO:统计连续签到的次数
     *
     * @param userid
     * @param date
     * @return
     */
//    private int getContinuousSignCount(Long userid, Date date) {
//        // 获取日期对应的天数，多少号，假设是 30
//        int dayOfMonth = DateUtil.dayOfMonth(date);
//        // 构建 Key
//        String signKey = buildSignKey(userid, date);
//        // bitfield user:sgin:5:202011 u30 0
//        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands.create()
//                .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth))
//                .valueAt(0);
//        List<Long> list = redisTemplate.opsForValue().bitField(signKey, bitFieldSubCommands);
//        if (list == null || list.isEmpty()) {
//            return 0;
//        }
//        int signCount = 0;
//        long v = list.get(0) == null ? 0 : list.get(0);
//        for (int i = dayOfMonth; i > 0; i--) {// i 表示位移操作次数
//            // 右移再左移，如果等于自己说明最低位是 0，表示未签到
//            if (v >> 1 << 1 == v) {
//                // 低位 0 且非当天说明连续签到中断了
//                if (i != dayOfMonth) break;
//            } else {
//                signCount++;
//            }
//            // 右移一位并重新赋值，相当于把最低位丢弃一位
//            v >>= 1;
//        }
//        return signCount;
//    }
//}


}
