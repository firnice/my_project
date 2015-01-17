package com.firnice.test.async.callback.impl;

import com.firnice.test.async.callback.Food;
import com.firnice.test.async.callback.ReceiveFood;
import com.firnice.test.async.callback.SendFood;

/**
 * Created by firnice on 15/1/18.
 */
public class XiaoCai implements ReceiveFood {

    private SendFood restaurant;

    public XiaoCai(SendFood restaurant) {
        this.restaurant = restaurant;
    }

    public void waitFood(final String foodName) {
        new Runnable() {
            @Override
            public void run() {
                restaurant.sendFood(XiaoCai.this, foodName);
            }
        }.run();
    }

    @Override
    public void receiveFood(Food food) {
        System.out.println(food.getFoodName()+" is receive !");
    }
}
