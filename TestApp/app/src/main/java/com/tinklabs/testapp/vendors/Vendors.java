package com.tinklabs.testapp.vendors;

import com.tinklabs.testapp.vendors.model.Vendor;

import java.util.List;


public interface Vendors {

    interface View {
        /**
         *  show a progress indicator when user pull down the list
         * @param active
         */
        void setProgressIndicator(boolean active);

        /**
         *  show vendor data in the list
         * @param vendors
         */
        void showVendors(List<Vendor> vendors);

        /**
         *  show more vendor data in the list
         * @param vendors
         */
        void showMore(List<Vendor> vendors);
    }

    interface Controller {
        /**
         *  load vendor data from service
         * @param category vendor's categroy
         */
        void loadVendors(int category);

        /**
         *  load more vender data from service
         * @param category vendor's categroy
         */
        void loadMore(int category);
    }
}
