package com.mp.cache;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CacheFactoryTest extends AbstractTest {

    @Test
    public void fun() {
        String key = "A";

        DataObject dataObject = CacheFactory.cache.getIfPresent(key);

        Assert.assertNull(dataObject);

        CacheFactory.cache.put(key, new DataObject("1"));

        dataObject = CacheFactory.cache.getIfPresent(key);

        assertNotNull(dataObject);

    }

    @Test
    public void fun2() {
        String key = "A";
        DataObject dataObject = CacheFactory.cache
                .get(key, k -> DataObject.get("Data for A"));

        assertNotNull(dataObject);
        assertEquals("Data for A", dataObject.getData());
        DataObject dataObject1 = CacheFactory.cache
                .getIfPresent(key);
        assertEquals("Data for A", dataObject1.getData());

    }

    @Test
    public void fun3(){
        String key = "A";

        DataObject dataObject = CacheFactory.loadingCache.get(key);

        assertNotNull(dataObject);
        assertEquals("Data for " + key, dataObject.getData());
    }


    @Test
    public void fun4(){
        Map<String, DataObject> dataObjectMap
                = CacheFactory.loadingCache.getAll(Arrays.asList("A", "B", "C"));

        assertEquals(3, dataObjectMap.size());
    }


    @Test
    public void fun5(){
        String key = "A";

        CacheFactory.asyncLoadingCache.get(key).thenAccept(dataObject -> {
            assertNotNull(dataObject);
            assertEquals("Data for " + key, dataObject.getData());
        });

        CacheFactory.asyncLoadingCache.getAll(Arrays.asList("A", "B", "C"))
                .thenAccept(dataObjectMap -> assertEquals(3, dataObjectMap.size()));
    }
}