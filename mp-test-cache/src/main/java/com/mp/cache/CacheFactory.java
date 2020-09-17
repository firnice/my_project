package com.mp.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Assert;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class CacheFactory implements InitializingBean {

    public static Cache<String, DataObject> cache;

    public static LoadingCache<String, DataObject> loadingCache;

    public static AsyncLoadingCache<String, DataObject> asyncLoadingCache;


    public void init() {
        cache = Caffeine.newBuilder()
                //设置缓存策略在1天未写入过期缓存
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();

        loadingCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> DataObject.get("Data for " + k));

        asyncLoadingCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> DataObject.get("Data for " + k));
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        init();



    }
}
