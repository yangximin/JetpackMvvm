package com.yang.jetpack.http.request;

import com.yang.jetpack.bean.ArticleBean;
import com.yang.jetpack.bean.ArticleListBean;
import com.yang.jetpack.bean.HomeBanner;
import com.yang.jetpack.bean.ImageBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by yxm on 2021/1/11.
 */
public interface ApiService {
    @GET
    Observable<ImageBean> getSplashImage(@Url String url, @Query("format") String format, @Query("idx") int idx, @Query("n") int n);

    /**
     * 获取首页banner
     *
     * @return
     */
    @GET("banner/json")
    Observable<List<HomeBanner>> getBanner();


    /**
     * 获取置顶文章
     *
     * @return
     */
    @GET("article/top/json")
    Observable<List<ArticleBean>> getTopArticleList();

    /**
     * 首页文章列表
     * 方法：GET
     *
     * @param page 页码
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<ArticleListBean> getArticleList(@Path("page") int page);
}
