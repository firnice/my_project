package com.firnice.test.async.callback;

import com.firnice.test.async.callback.impl.Restaurant;
import com.firnice.test.async.callback.impl.XiaoCai;

/**
 * Created by firnice on 15/1/18.
 */
public class TestMain {

    public static void main(String[] args){
        SendFood sendFood = new Restaurant();
        XiaoCai xiaoCai = new XiaoCai(sendFood);
        System.out.println("要个拍黄瓜！");
        xiaoCai.waitFood("拍黄瓜");
        System.out.println("do someThing~~~~");
    }
}
