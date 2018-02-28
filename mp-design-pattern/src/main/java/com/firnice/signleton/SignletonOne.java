package com.firnice.signleton;

/**
 * @author: liyl
 * @date: 2018/2/28 上午11:35
 * @since 1.0.0
 */
public class SignletonOne {

    //静态私有变量
    private static SignletonOne instance = null;

    //私有的构造函数
    private SignletonOne() {}

    private static SignletonOne getInstance() {
        if (instance == null) {
            instance = new SignletonOne();
        }

        return instance;
    }

}
