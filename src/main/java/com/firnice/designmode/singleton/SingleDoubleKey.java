package com.firnice.designmode.singleton;

/**
 * @author: liyl
 * @date: 2017/9/5 下午7:54
 * @since 1.0.0
 * <p>
 * 双锁验证
 */
public class SingleDoubleKey {

    private static SingleDoubleKey singleDoubleKey = null;

    private SingleDoubleKey() {
    }

    //虽然比懒汉模式提升了效率，但解决不了并发问题！
    public SingleDoubleKey getInstance() {
        if (singleDoubleKey == null) {
            synchronized(SingleDoubleKey.class) {
                if (singleDoubleKey == null) {
                    singleDoubleKey = new SingleDoubleKey();
                }
            }
        }

        return singleDoubleKey;
    }
}
