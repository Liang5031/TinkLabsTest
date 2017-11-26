package com.tinklabs.testapp.vendors;

import com.tinklabs.testapp.vendors.model.Vendor;

import java.util.List;

public interface VendorsService {

    interface VendorsServiceCallback<T> {
        /**
         * Callback from service when loading finished
         * @param vendors vendors data
         */
        void onVendorsLoaded(T vendors);
    }

    /**
     * Load vendors data from server
     *
     * @param category      which category to load
     * @param start         identify the start point of this loading
     * @param num           how many vendors will be load
     * @param callback     called when loading finished
     */
    void loadVendors(int category, int start, int num, VendorsServiceCallback<List<Vendor>> callback);
}
