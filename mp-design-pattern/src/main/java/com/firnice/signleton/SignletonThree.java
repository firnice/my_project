package com.firnice.signleton;

/**
 * @author: liyl
 * @date: 2018/2/28 上午11:35
 * @since 1.0.0
 */
public class SignletonThree {

    //静态私有变量
    private static SignletonThree instance = null;

    //私有的构造函数
    private SignletonThree() {}

    //增加锁
    private static SignletonThree getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new SignletonThree();
                }
            }
        }

        return instance;
    }

}
