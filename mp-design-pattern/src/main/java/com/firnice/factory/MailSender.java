package com.firnice.factory;

/**
 * @author: liyl
 * @date: 2018/2/28 下午4:12
 * @since 1.0.0
 */
public class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("mailsender");
    }
}
