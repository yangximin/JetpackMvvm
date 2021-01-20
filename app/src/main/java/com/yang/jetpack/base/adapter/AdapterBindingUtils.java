package com.yang.jetpack.base.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.yang.jetpack.R;
import com.yang.jetpack.adapter.holder.BannerViewHolder;
import com.yang.jetpack.bean.ArticleBean;
import com.yang.jetpack.bean.HomeBanner;
import com.yang.jetpack.bean.home.BannerData;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.List;

/**
 * Created by yxm on 2021/1/18.
 */
public class AdapterBindingUtils {
    @BindingAdapter("bannerData")
    public void setBannerData(MZBannerView banner, BannerData bannerData) {
        if (bannerData == null || bannerData.getHomeBanners() == null) {
            return;
        }
        List<HomeBanner> movies = bannerData.getHomeBanners();
        banner.setPages(movies, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        banner.start();
    }

    @BindingAdapter("visibility")
    public static void setViewVisibility(View view, Boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("isCollect")
    public static void isCollect(ImageView imageView, boolean collect) {
        if (collect) {
            imageView.setImageResource(R.mipmap.ic_collect_yes);
        } else

            imageView.setImageResource(R.mipmap.ic_collect_no);
    }
    @BindingAdapter("countText")
    public static void setTextCount(TextView tv, String count) {
        tv.setText(count);
    }

    @BindingAdapter("articleTag")
    public static void setArticleTag(TextView view, ArticleBean articleBean) {
        if (articleBean != null && articleBean.getTags() != null) {
            if (articleBean.getTags().size() == 1) {
                view.setText(articleBean.getTags().get(0).getName());
            } else if (articleBean.getTags().size() >= 2) {
                String str = articleBean.getTags().get(0).getName() + articleBean.getTags().get(1).getName();
                view.setText(str);
            } else {
                view.setText("其他");
            }
        }
    }

}
