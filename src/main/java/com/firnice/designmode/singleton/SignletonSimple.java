package com.firnice.designmode.singleton;

/**
 * @author: liyl
 * @date: 2017/9/5 上午9:03
 * @since 1.0.0
 * <p>
 * 饿汉模式
 *
 * jvm启动的时候 就初始化 占内存
 */
public class SignletonSimple {

    private static SignletonSimple signletonSimple = new SignletonSimple();

    //私有的构造函数 不让在其他类里new出来
    private SignletonSimple() {}

    public static SignletonSimple getInstance() {
        return signletonSimple;
    }

}
