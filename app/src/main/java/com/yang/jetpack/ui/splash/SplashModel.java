package com.yang.jetpack.ui.splash;

import com.yang.jetpack.base.model.BaseModel;
import com.yang.jetpack.bean.ImageBean;
import com.yang.jetpack.http.data.CommonDisposable;
import com.yang.jetpack.http.request.ApiService;
import com.yang.jetpack.http.request.HttpClient;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by yxm on 2021/1/12.
 */
public class SplashModel extends BaseModel {

    public void requestImages(CommonDisposable<?> observer) {
        Observable<ImageBean> observable = HttpClient.getInstance()
                .createService(ApiService.class)
                .getSplashImage("https://www.bing.com/HPImageArchive.aspx", "js", 0, 1);
        executeOriginal(observable, observer);

    }
}
