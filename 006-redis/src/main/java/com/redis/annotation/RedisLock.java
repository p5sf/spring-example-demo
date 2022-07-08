package com.redis.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/***
 * @author Yan
 * @description: 分布式锁注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {

    String key();

    int expire() default 5;

    long timeout() default Long.MIN_VALUE;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
