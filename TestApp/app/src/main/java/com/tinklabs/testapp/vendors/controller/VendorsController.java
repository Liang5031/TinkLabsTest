package com.tinklabs.testapp.vendors.controller;

import android.support.annotation.NonNull;

import com.tinklabs.testapp.vendors.Vendors;
import com.tinklabs.testapp.vendors.VendorsService;
import com.tinklabs.testapp.vendors.model.Vendor;

import java.util.List;

/**
 * This is a controller between the views and service.
 */
public class VendorsController implements Vendors.Controller {

    private final Vendors.View mVendersView;
    private final VendorsService mVenderService;


    public VendorsController(@NonNull VendorsService vendorsService, @NonNull Vendors.View vendersView){
        mVendersView = vendersView;
        mVenderService = vendorsService;
    }

    @Override
    public void loadVendors(int category) {
        /**
         * ignore the parameter 'start' and 'num' because we are using {@link MockVendorsService}
         * for real server we should pass these two parameters to load different vendors.
         */
        mVenderService.loadVendors(category, -1, -1, new VendorsService.VendorsServiceCallback<List<Vendor>>() {
            @Override
            public void onVendorsLoaded(List<Vendor> vendors) {
                mVendersView.showVendors(vendors);
                mVendersView.setProgressIndicator(false);
            }
        });
    }

    @Override
    public void loadMore(int category) {
        /**
         * ignore the parameter 'start' and 'num' because we are using {@link MockVendorsService}
         * for real server we should pass these two parameters to load different vendors.
         */
        mVenderService.loadVendors(category, -1, -1, new VendorsService.VendorsServiceCallback<List<Vendor>>() {
            @Override
            public void onVendorsLoaded(List<Vendor> vendors) {
                mVendersView.showMore(vendors);
            }
        });
    }
}
