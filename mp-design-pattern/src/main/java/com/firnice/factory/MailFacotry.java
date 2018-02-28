package com.firnice.factory;

/**
 * @author: liyl
 * @date: 2018/2/28 下午4:22
 * @since 1.0.0
 */
public class MailFacotry implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
