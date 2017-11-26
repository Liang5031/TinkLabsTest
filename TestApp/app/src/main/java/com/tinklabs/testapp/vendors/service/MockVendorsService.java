package com.tinklabs.testapp.vendors.service;

import android.content.Context;
import android.content.res.AssetManager;

import com.tinklabs.testapp.utils.CategoryUtils;
import com.tinklabs.testapp.vendors.VendorsService;
import com.tinklabs.testapp.vendors.model.Vendor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This is mock service since I don't have a server.
 * We can use volley or OkHttp for real server.
 */
public class MockVendorsService implements VendorsService {

    static final String KEY_VENDORS = "vendors";
    static final String KEY_STYLE = "style";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "desc";
    static final String KEY_IMAGE_URL = "imageUrl";

    static final String JSON_FILE_NAME_CITY_GUIDE = "cityGuide.json";
    static final String JSON_FILE_NAME_SHOP = "shop.json";
    static final String JSON_FILE_NAME_EAT = "eat.json";

    private final Context mContext;
    public MockVendorsService(Context ctx){
        mContext = ctx;
    }

    @Override
    public void loadVendors(int category, int start, int num, VendorsServiceCallback<List<Vendor>> callback) {
        /* we should load data from a background thread if we load it from real server */
        JSONObject json = loadJsonFromAsset(category, mContext);
        List<Vendor> vendors = getVendors(json);
        callback.onVendorsLoaded(vendors);
    }

    public JSONObject loadJsonFromAsset(int category, Context ctx){
        JSONObject jsonObj = null;

        String json = getJsonString(getFileName(category), ctx);

        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    public String getJsonString(String fileName, Context ctx) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = ctx.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public List<Vendor> getVendors(JSONObject jsonObject){
        List<Vendor> vendors = new ArrayList<Vendor>();
        try {
            JSONArray items = jsonObject.getJSONArray(KEY_VENDORS);

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                Vendor vendor = new Vendor();
                vendor.setTitle(item.getString(KEY_TITLE));
                vendor.setDesc(item.getString(KEY_DESCRIPTION));
                vendor.setImageUrl(item.getString(KEY_IMAGE_URL));
                vendor.setStyle(item.getInt(KEY_STYLE));
                vendors.add(vendor);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return vendors;
    }

    String getFileName(int category){
        switch (category){
            case CategoryUtils.CATEGORY_CITY_GUIDE:
                return JSON_FILE_NAME_CITY_GUIDE;
            case CategoryUtils.CATEGORY_SHOP:
                return JSON_FILE_NAME_SHOP;
            case CategoryUtils.CATEGORY_EAT:
                return JSON_FILE_NAME_EAT;
            default:
                break;
        }

        return "";
    }
}
