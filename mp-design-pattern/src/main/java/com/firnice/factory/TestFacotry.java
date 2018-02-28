package com.firnice.factory;

/**
 * @author: liyl
 * @date: 2018/2/28 下午4:22
 * @since 1.0.0
 */
public class TestFacotry {

    public static void main(String[] args) {
        Provider provider = new MailFacotry();
        Sender produce = provider.produce();
        produce.Send();
        System.out.println(new Integer[] {1, 2, 3});
    }
}
