package com.yang.jetpack.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yang.jetpack.R;
import com.yang.jetpack.bean.HomeBanner;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * @author : devel
 * @date : 2020/2/20 8:55
 * @desc :
 */
public class BannerViewHolder implements MZViewHolder<HomeBanner> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.remote_banner_item, null);
        mImageView = (ImageView) view.findViewById(R.id.remote_item_image);
        return view;
    }

    @Override
    public void onBind(Context context, int i, HomeBanner homeBanner) {
        Glide.with(context).load(homeBanner.getImagePath()).into(mImageView);
    }
}