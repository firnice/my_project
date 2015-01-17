package com.firnice.test.async.callback.impl;

import com.firnice.test.async.callback.Food;
import com.firnice.test.async.callback.ReceiveFood;
import com.firnice.test.async.callback.SendFood;

/**
 * Created by firnice on 15/1/17.
 */
public class Restaurant implements SendFood {
    @Override
    public void sendFood(ReceiveFood receiveFood, String foodName) {
        Food food = cook(foodName);
        receiveFood.receiveFood(food);
    }

    private Food cook(String foodName) {
        Food someFood = new Food(foodName);
        try {
            Thread.currentThread().sleep(2000);
            System.out.println("厨师睡醒了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(foodName+"完成了！");
        return someFood;
    }
}
