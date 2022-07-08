package com.redis.aspect;//package com.redis.aspect;
//
//import com.redis.annotation.RedisLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author YanZhao
// * @description:定义拦截器
// * @date 2022年06月03日 10:02
// */
//
//@Aspect
//@Component
//public class LockMethodAspect {
//    @Autowired
//    private RedisLockHelper redisLockHelper;
//    @Autowired
//    private JedisUtil jedisUtil;
//    private Logger logger = LoggerFactory.getLogger(LockMethodAspect.class);
//
//    @Around("@annotation(com.redis.lock.annotation.RedisLock)")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        Jedis jedis = jedisUtil.getJedis();
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//
//        RedisLock redisLock = method.getAnnotation(RedisLock.class);
//        String value = UUID.randomUUID().toString();
//        String key = redisLock.key();
//        try {
//            final boolean islock = redisLockHelper.lock(jedis,key, value, redisLock.expire(), redisLock.timeUnit());
//            logger.info("isLock : {}",islock);
//            if (!islock) {
//                logger.error("获取锁失败");
//                throw new RuntimeException("获取锁失败");
//            }
//            try {
//                return joinPoint.proceed();
//            } catch (Throwable throwable) {
//                throw new RuntimeException("系统异常");
//            }
//        }  finally {
//            logger.info("释放锁");
//            redisLockHelper.unlock(jedis,key, value);
//            jedis.close();
//        }
//    }
//}
