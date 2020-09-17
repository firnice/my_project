package com.mp.cache;

public class DataObject {
    private String data;

    private static int objectCounter = 0;

    public DataObject(String data) {
        this.data =data;
    }
    // standard constructors/getters


    public String getData() {
        return data;
    }

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }
}
