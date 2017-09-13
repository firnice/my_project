package com.firnice.test.se.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: liyl
 * @date: 2017/2/6 下午6:26
 * @since 1.0.0
 *
 *  Collections.EMPTY_LIST 是无法新增的
 */
public class EmptyListTest {

    @Test
    public void testEmptyList() {
        List a = Collections.EMPTY_LIST;
        List b = new ArrayList();
        b.add(1);
        try {
            a.add(2);
            a.addAll(b);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertEquals(e.getClass(),UnsupportedOperationException.class);
        }
    }
}


