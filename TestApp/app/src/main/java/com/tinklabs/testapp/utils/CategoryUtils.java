package com.tinklabs.testapp.utils;

/**
 * utility for category information
 */
public class CategoryUtils {
    public static final int CATEGORY_CITY_GUIDE = 0;
    public static final int CATEGORY_SHOP = 1;
    public static final int CATEGORY_EAT = 2;

    static final String CATEGORY_NAME_CITY_GUIDE = "City guide";
    static final String CATEGORY_NAME_SHOP = "Shop";
    static final String CATEGORY_NAME_EAT = "Eat";

    static final String CATEGORIE_NAMES[] = new String[]{CATEGORY_NAME_CITY_GUIDE,CATEGORY_NAME_SHOP,CATEGORY_NAME_EAT};
    static final int CATEGORIES[] = new int[]{CATEGORY_CITY_GUIDE, CATEGORY_SHOP, CATEGORY_EAT};

    public static String getCategoryName(int position){
        return CATEGORIE_NAMES[position];
    }

    public static int getCount(){
        return CATEGORIE_NAMES.length;
    }

    public static int getCategory(int postion){
        return CATEGORIES[postion];
    }
}
