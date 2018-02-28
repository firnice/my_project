package com.firnice.factory;

/**
 * @author: liyl
 * @date: 2018/2/28 下午4:21
 * @since 1.0.0
 */
public class SmsFacotry implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
