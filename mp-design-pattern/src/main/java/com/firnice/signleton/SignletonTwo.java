package com.firnice.signleton;

/**
 * @author: liyl
 * @date: 2018/2/28 上午11:35
 * @since 1.0.0
 */
public class SignletonTwo {

    //静态私有变量
    private static SignletonTwo instance = null;

    //私有的构造函数
    private SignletonTwo() {}

    //增加锁
    private static synchronized SignletonTwo getInstance() {
        if (instance == null) {
            instance = new SignletonTwo();
        }

        return instance;
    }

}
