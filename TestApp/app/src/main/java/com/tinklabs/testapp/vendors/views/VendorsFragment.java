package com.tinklabs.testapp.vendors.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinklabs.testapp.R;
import com.tinklabs.testapp.utils.CategoryUtils;
import com.tinklabs.testapp.vendors.Vendors;
import com.tinklabs.testapp.vendors.controller.VendorsController;
import com.tinklabs.testapp.vendors.model.Vendor;
import com.tinklabs.testapp.vendors.service.MockVendorsService;

import java.util.ArrayList;
import java.util.List;


/**
 * Display a list of {@link Vendor }s
 */
public class VendorsFragment extends Fragment implements Vendors.View {

    public static final String ARG_POSITION = "position";
    private int mTabPosition;

    private RecyclerView mRecyclerView;
    private VendorsAdapter mListAdapter;
    private Vendors.Controller mController;

    public static VendorsFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        VendorsFragment pageFragment = new VendorsFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabPosition = getArguments().getInt(ARG_POSITION);
        mListAdapter = new VendorsAdapter(this.getContext(), new ArrayList<Vendor>(0));
        mController = new VendorsController(new MockVendorsService(this.getContext()), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mController.loadVendors(CategoryUtils.getCategory(mTabPosition));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendors, container, false);

        mRecyclerView =(RecyclerView)view.findViewById(R.id.vendorList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mListAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
                int visibleItemCount = recyclerView.getChildCount();

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition == totalItemCount - 1
                        && visibleItemCount > 0) {
                    mController.loadMore(CategoryUtils.getCategory(mTabPosition));
                }
            }
        });

        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.loadVendors(mTabPosition);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showVendors(List<Vendor> vendors) {
        mListAdapter.refresh(vendors);
    }

    @Override
    public void showMore(List<Vendor> vendors) {
        mListAdapter.addMore(vendors);
    }
}