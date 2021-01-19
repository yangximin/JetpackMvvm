package com.yang.jetpack.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.yang.jetpack.base.viewmodel.BaseViewModel;
import com.yang.jetpack.bean.ArticleBean;
import com.yang.jetpack.bean.ArticleListBean;
import com.yang.jetpack.bean.home.HomeDate;
import com.yang.jetpack.config.LoadState;
import com.yang.jetpack.http.data.CommonDisposable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxm on 2021/1/18.
 */
public class HomeViewModel extends BaseViewModel<HomeModel> {


    MutableLiveData<List<HomeDate>> mHomeDateList;
    List<HomeModel> mList;
    private int mIndex = 0;

    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }

    @Override
    protected void init() {
        mHomeDateList = new MutableLiveData<>();
        loadHomeData();
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        loadState.postValue(LoadState.LOADING);
    }

    public void loadHomeData() {
        mIndex = 0;
        mModel.loadHomeData(new CommonDisposable<List<HomeDate>>(this) {
            @Override
            public void onNext(@io.reactivex.annotations.NonNull List<HomeDate> homeDates) {
                super.onNext(homeDates);
//                if (mHomeDateList.getValue() != null) {
//                    mHomeDateList.getValue().clear();
//                }
//                mHomeDateList.setValue(homeDates);
                mHomeDateList.postValue(homeDates);
            }
        });
    }

    public void getArticleList() {
        mModel.getArticleList(mIndex, new CommonDisposable<ArticleListBean>(this) {
            @Override
            public void onNext(@io.reactivex.annotations.NonNull ArticleListBean articleListBean) {
                super.onNext(articleListBean);
                List<HomeDate> homeDates = new ArrayList<>();
                List<ArticleBean> datas = articleListBean.getDatas();
                if (datas != null && !datas.isEmpty()) {
                    for (ArticleBean articleBean : datas) {
                        homeDates.add(new HomeDate(2, articleBean));
                    }
                }
//                if (mHomeDateList.getValue() != null) {
//                    mHomeDateList.getValue().addAll(homeDates);
//                }
                mHomeDateList.postValue(homeDates);
            }
        });
    }
}
