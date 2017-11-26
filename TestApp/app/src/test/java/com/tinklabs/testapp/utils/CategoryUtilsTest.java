package com.tinklabs.testapp.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CategoryUtilsTest {

    @Test
    public void testGetCategoryName(){
        String name = CategoryUtils.getCategoryName(0);
        assertEquals(CategoryUtils.CATEGORY_NAME_CITY_GUIDE, name);

        name = CategoryUtils.getCategoryName(1);
        assertEquals(CategoryUtils.CATEGORY_NAME_SHOP, name);

        name = CategoryUtils.getCategoryName(2);
        assertEquals(CategoryUtils.CATEGORY_NAME_EAT, name);
    }

    @Test
    public void testGetCount(){
        int count = CategoryUtils.getCount();
        assertEquals(3, count);
    }

    @Test
    public void testGetCategoryType(){
        int type = CategoryUtils.getCategory(0);
        assertEquals(CategoryUtils.CATEGORY_CITY_GUIDE, type);

        type = CategoryUtils.getCategory(1);
        assertEquals(CategoryUtils.CATEGORY_SHOP, type);

        type = CategoryUtils.getCategory(2);
        assertEquals(CategoryUtils.CATEGORY_EAT, type);
    }
}
