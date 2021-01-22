package com.yang.jetpack.bean.home;

import com.yang.jetpack.bean.HomeBanner;

import java.util.List;

/**
 * Created by yxm on 2021/1/18.
 */
public class BannerData {

    List<HomeBanner> homeBanners;

    public BannerData(List<HomeBanner> homeBanners) {
        this.homeBanners = homeBanners;
    }

    public List<HomeBanner> getHomeBanners() {
        return homeBanners;
    }

    public void setHomeBanners(List<HomeBanner> homeBanners) {
        this.homeBanners = homeBanners;
    }
}
