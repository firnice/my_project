package com.firnice.factory;

/**
 * @author: liyl
 * @date: 2018/2/28 下午4:13
 * @since 1.0.0
 */
public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("smssender");
    }
}
