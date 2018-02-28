package com.firnice.signleton;

/**
 * @author: liyl
 * @date: 2018/2/28 上午11:35
 * @since 1.0.0
 */
public class SignletonFour {

    //私有的构造函数
    private SignletonFour() {}

    /* 此处使用一个内部类来维护单例 */
    private static class SignletonFactory {
        private static SignletonFour instance = new SignletonFour();
    }

    public static SignletonFour getInstance() {
        return SignletonFactory.instance;
    }
}
