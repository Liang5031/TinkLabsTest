package com.tinklabs.testapp.vendors.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.tinklabs.testapp.R;
import com.tinklabs.testapp.vendors.model.Vendor;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;


public class VendorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Vendor> mVendors = new ArrayList<Vendor>();
    Context mContext;

    public VendorsAdapter(Context context, List<Vendor> vendors) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        mVendors = vendors;
    }

    @SuppressLint("RestrictedApi")
    public void refresh(List<Vendor> vendors){
        mVendors = checkNotNull(vendors);;
        notifyDataSetChanged();
    }

    @SuppressLint("RestrictedApi")
    public void addMore(List<Vendor> vendors){
        checkNotNull(mVendors);
        mVendors.addAll(vendors);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Vendor.STYLE_IMAGE_ONLY) {
            return new ImageViewHolder(mInflater.inflate(R.layout.item_vendor_image_only, parent, false));
        } else {
            return new DetailViewHolder(mInflater.inflate(R.layout.item_vendor_detail, parent, false));
        }
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Vendor vendor = mVendors.get(position);

        if (holder instanceof DetailViewHolder) {
            ((DetailViewHolder)holder).title.setText(vendor.getTitle());
            ((DetailViewHolder)holder).description.setText(vendor.getDesc());
            Glide.with(mContext).load(vendor.getImageUrl()).error(R.mipmap.def_image).into(((DetailViewHolder)holder).image);

/*            Glide.with(mContext)
                    .load(vendor.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(new GlideDrawableImageViewTarget(((DetailViewHolder)holder).image) {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                        }
                    });*/

        } else if (holder instanceof ImageViewHolder) {
            Glide.with(mContext).load(vendor.getImageUrl()).error(R.mipmap.def_image).into(((ImageViewHolder)holder).image);

/*            Glide.with(mContext)
                    .load(vendor.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(new GlideDrawableImageViewTarget(((ImageViewHolder)holder).image) {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                        }
                    });*/
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = Vendor.STYLE_IMAGE_ONLY;
        if(mVendors.size()>position) {
            Vendor vendor = mVendors.get(position);
            viewType = vendor.getStyle();
        }

        return viewType;
    }

    @Override
    public int getItemCount() {
        return mVendors.size();
    }

    /* viewHolder for {@link Vendor.STYLE_IMAGE_ONLY } */
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ImageViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivImage);
        }
    }

    /* viewHolder for {@link Vendor.STYLE_DETAIL } */
    public static class DetailViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView description;

        public DetailViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.ivImage);
            title = (TextView) view.findViewById(R.id.tvTitle);
            description = (TextView) view.findViewById(R.id.tvDesc);
        }
    }
}
