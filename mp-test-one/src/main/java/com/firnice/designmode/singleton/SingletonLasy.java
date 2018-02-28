package com.firnice.designmode.singleton;

/**
 * @author: liyl
 * @date: 2017/9/5 上午9:08
 * @since 1.0.0
 * <p>
 * 懒汉模式    调用的时候才生成
 */
public class SingletonLasy {

    private SingletonLasy() {
    }

    private static SingletonLasy singletonLasy = null;

    //线程同步问题 可能出现多个实例
    public static SingletonLasy getInstance() {
        if (singletonLasy == null) {
            singletonLasy = new SingletonLasy();
        }
        return singletonLasy;
    }

    public static synchronized SingletonLasy getSycInstance() {
        if (singletonLasy == null) {
            singletonLasy = new SingletonLasy();
        }
        return singletonLasy;
    }
}
