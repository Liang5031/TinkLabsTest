package com.tinklabs.testapp.vendors.controller;


import com.tinklabs.testapp.utils.CategoryUtils;
import com.tinklabs.testapp.vendors.Vendors;
import com.tinklabs.testapp.vendors.VendorsService;
import com.tinklabs.testapp.vendors.model.Vendor;
import com.tinklabs.testapp.vendors.service.MockVendorsService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

public class VendorsControllerTest {

    private static List<Vendor> VENDORS = new ArrayList<Vendor>();

    @Mock
    private Vendors.View mVendorsView;

    @Mock
    private MockVendorsService mVendorsService;

    @Captor
    private ArgumentCaptor<VendorsService.VendorsServiceCallback> mLoadVendorsCallbackCaptor;

    private VendorsController mVendorsController;

    @Before
    public void setupVendorsController() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mVendorsController = new VendorsController(mVendorsService, mVendorsView);
        VENDORS.add(new Vendor("title1", "desc1", "imageUrl1", Vendor.STYLE_DETAIL));
        VENDORS.add(new Vendor("title2", "desc2", "imageUrl2", Vendor.STYLE_DETAIL));
    }

    @Test
    public void loadVendors(){
        mVendorsController.loadVendors(CategoryUtils.CATEGORY_CITY_GUIDE);

        // Callback is captured and invoked with stubbed notes
        verify(mVendorsService).loadVendors(anyInt(), anyInt(), anyInt(), mLoadVendorsCallbackCaptor.capture());
        mLoadVendorsCallbackCaptor.getValue().onVendorsLoaded(VENDORS);

        // Then progress indicator is hidden and notes are shown in UI
        verify(mVendorsView).setProgressIndicator(false);
        verify(mVendorsView).showVendors(VENDORS);
    }

    @Test
    public void loadMore(){
        mVendorsController.loadVendors(CategoryUtils.CATEGORY_CITY_GUIDE);

        // Callback is captured and invoked with stubbed notes
        verify(mVendorsService).loadVendors(anyInt(), anyInt(), anyInt(), mLoadVendorsCallbackCaptor.capture());
        mLoadVendorsCallbackCaptor.getValue().onVendorsLoaded(VENDORS);

        // Then progress indicator is hidden and notes are shown in UI
        verify(mVendorsView).setProgressIndicator(false);
        verify(mVendorsView).showVendors(VENDORS);
    }

}
