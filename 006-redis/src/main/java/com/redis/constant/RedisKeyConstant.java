package com.redis.constant;

/**
 * @author YanZhao
 * @description
 * @date 2022年06月01日 15:31
 */

public enum RedisKeyConstant {

    verify_code("verify_code:", "验证码"),
    skill_code("skill_code:", "秒杀券的key"),
    lock_key("lockby:", "分布式锁的key"),
    following("following:", "关注集合Key"),
    followers("followers:", "粉丝集合key"),
    following_feeds("following_feeds:", "我关注的好友的FeedsKey"),
    global_rank("global_rank","排行榜"),
    ;

    private String key;
    private String desc;

    RedisKeyConstant(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
