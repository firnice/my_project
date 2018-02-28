package com.firnice.designmode.singleton;

/**
 * @author: liyl
 * @date: 2017/9/6 上午9:39
 * @since 1.0.0
 */
public class SingletonInnerClass {

    private SingletonInnerClass() {

    }

    private static class SingletonFactory {
        private static SingletonInnerClass instance = new SingletonInnerClass();
    }

    /*单例模式使用内部类来维护单例的实现，JVM内部的机制能够保证当一个类被加载的时候，
    这个类的加载过程是线程互斥的。这样当我们第一次调用getInstance的时候，JVM能够帮我们保证instance只被创建一次，
    并且会保证把赋值给instance的内存初始化完毕，这样我们就不用担心上面的问题。
    同时该方法也只会在第一次调用的时候使用互斥机制，这样就解决了低性能问题。
     */

    private static SingletonInnerClass getInstance() {
        return SingletonFactory.instance;
    }
}
