package com.firnice.test.async.callback;

/**
 * Created by firnice on 15/1/17.
 */
public class Food {
    public Food(String foodName) {
        this.foodName = foodName;
    }

    private String foodName;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
