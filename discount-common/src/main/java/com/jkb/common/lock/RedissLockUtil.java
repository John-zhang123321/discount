//package com.jkb.common.lock;
//
//import org.redisson.api.RLock;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author: zh
// * @Date: 2018/6/20 14:04
// * @Description:
// * 分布式锁基于Redisson的实现
// *  建议使用 tryLock 作为分布式锁（如果任务完成，可以配合unLock()使用）
// *  而 lock(String key) 需要与unLock 一起使用。 未获得锁的线程会一直等待，直到获得锁。
// *
// */
//public class RedissLockUtil {
//    private static DistributedLocker redissLock;
//
//    public static void setLocker(DistributedLocker locker) {
//        redissLock = locker;
//    }
//
//    /**
//     * 加锁 （30S后默认释放）
//     * @param lockKey
//     * @return
//     */
//    public static RLock lock(String lockKey) {
//        return redissLock.lock(lockKey);
//    }
//
//    /**
//     * 释放锁 - 通过key值主动释放锁
//     * @param lockKey
//     */
//    public static void unlock(String lockKey) {
//        redissLock.unlock(lockKey);
//    }
//
//    /**
//     * 释放锁- 通过RLock主动释放锁
//     * @param lock
//     */
//    public static void unlock(RLock lock) {
//        redissLock.unlock(lock);
//    }
//
//    /**
//     * 带超时的锁
//     * @param lockKey
//     * @param timeout 超时时间   单位：秒
//     */
//    public static RLock lock(String lockKey, int timeout) {
//        return redissLock.lock(lockKey, timeout);
//    }
//
//    /**
//     * 带超时的锁
//     * @param lockKey
//     * @param unit 时间单位
//     * @param timeout 超时时间
//     */
//    public static RLock lock(String lockKey, TimeUnit unit ,int timeout) {
//        return redissLock.lock(lockKey, unit, timeout);
//    }
//
//    /**
//     * 尝试获取锁
//     * @param lockKey
//     * @param waitTime 最多等待时间
//     * @param leaseTime 上锁后自动释放锁时间
//     * @return
//     */
//    public static boolean tryLock(String lockKey, int waitTime, int leaseTime) {
//        return redissLock.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
//    }
//
//    /**
//     * 尝试获取锁
//     * @param lockKey
//     * @param unit 时间单位
//     * @param waitTime 最多等待时间
//     * @param leaseTime 上锁后自动释放锁时间
//     * @return
//     */
//    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
//        return redissLock.tryLock(lockKey, unit, waitTime, leaseTime);
//    }
//}
