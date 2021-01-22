package com.yang.jetpack.ui.home;

import android.annotation.SuppressLint;

import com.yang.jetpack.base.model.BaseModel;
import com.yang.jetpack.bean.ArticleBean;
import com.yang.jetpack.bean.ArticleListBean;
import com.yang.jetpack.bean.HomeBanner;
import com.yang.jetpack.bean.home.BannerData;
import com.yang.jetpack.bean.home.HomeDate;
import com.yang.jetpack.http.data.BaseResponse;
import com.yang.jetpack.http.request.ApiService;
import com.yang.jetpack.http.request.HttpClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function3;

/**
 * Created by yxm on 2021/1/18.
 */
public class HomeModel extends BaseModel {


    @SuppressLint("CheckResult")
    public void loadHomeData(Observer<?> observer) {
        ApiService service = HttpClient.getInstance().createService(ApiService.class);
        Observable<List<HomeDate>> zip = Observable.zip(service.getBanner(), service.getTopArticleList(), service.getArticleList(0), new Function3<List<HomeBanner>, List<ArticleBean>, ArticleListBean, List<HomeDate>>() {
            @NonNull
            @Override
            public List<HomeDate> apply(@NonNull List<HomeBanner> homeBanners, @NonNull List<ArticleBean> articleBeans, @NonNull ArticleListBean articleListBean) throws Exception {
                List<HomeDate> homeDates = new ArrayList<>();
                BannerData bannerData = new BannerData(homeBanners);
                homeDates.add(new HomeDate(0, bannerData));
                for (ArticleBean articleBean : articleBeans) {
                    homeDates.add(new HomeDate(1, articleBean));
                }
                List<ArticleBean> datas = articleListBean.getDatas();
                if (datas != null && !datas.isEmpty()) {
                    for (ArticleBean articleBean : datas) {
                        homeDates.add(new HomeDate(2, articleBean));
                    }
                }
                return homeDates;
            }
        });
        executeOriginal(zip, observer);
//        ApiService service = HttpClient.getInstance().createService(ApiService.class);
//        Observable<?> zip = Observable.zip(service.getBanner(), service.getTopArticleList(), service.getArticleList(0), new Function3<BaseResponse<List<HomeBanner>>, BaseResponse<List<ArticleBean>>, BaseResponse<ArticleListBean>, BaseResponse<List<HomeDate>>>() {
//            @NonNull
//            @Override
//            public BaseResponse<List<HomeDate>> apply(@NonNull BaseResponse<List<HomeBanner>> homeBanners, @NonNull BaseResponse<List<ArticleBean>> articleBeans, @NonNull BaseResponse<ArticleListBean> articleListBean) throws Exception {
//                List<HomeDate> homeDates = new ArrayList<>();
//                homeDates.add(new HomeDate(0, homeBanners.getData()));
//                for (ArticleBean articleBean : articleBeans.getData()) {
//                    homeDates.add(new HomeDate(1, articleBean));
//                }
//                List<ArticleBean> datas = articleListBean.getData().getDatas();
//                if (datas != null && !datas.isEmpty()) {
//                    for (ArticleBean articleBean : datas) {
//                        homeDates.add(new HomeDate(2, articleBean));
//                    }
//                }
//                BaseResponse<List<HomeDate>> listBaseResponse = new BaseResponse<>();
//                listBaseResponse.setData(homeDates);
//                listBaseResponse.setErrorCode(0);
//                return listBaseResponse;
//            }
//        });
//        execute(zip, observer);
    }



    public void getArticleList(int page, Observer<?> observer) {
        Observable<ArticleListBean> articleList = HttpClient.getInstance().createService(ApiService.class).getArticleList(page);
        execute(articleList, observer);
    }
}
