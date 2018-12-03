package com.cahce;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;

public class GuavaCache {

    public static void main(String[] args) {
        Cache<String, List> cache = CacheBuilder.newBuilder().maximumSize(3).recordStats().build();
    }
}
