package com.yang.jetpack.ui.splash;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.yang.jetpack.base.utils.L;
import com.yang.jetpack.base.viewmodel.BaseViewModel;
import com.yang.jetpack.bean.ActivitySkip;
import com.yang.jetpack.bean.ImageBean;
import com.yang.jetpack.http.data.CommonDisposable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by yxm on 2021/1/12.
 */
public class SplashViewModel extends BaseViewModel<SplashModel> {

    private MutableLiveData<ImageBean> mImage;

    private MutableLiveData<String> mTimer;

    private MutableLiveData<ActivitySkip> mActivitySkip;

    public SplashViewModel() {
        mImage = new MutableLiveData<>();
        mTimer = new MutableLiveData<>();
        mActivitySkip = new MutableLiveData<>();
        mTimer.postValue("跳过：5s");
    }

    private void request() {
        mModel.requestImages(new CommonDisposable<ImageBean>(this) {

            @Override
            public void onNext(@NotNull ImageBean imageBean) {
                super.onNext(imageBean);
                mImage.postValue(imageBean);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                super.onError(e);
            }
        });
    }

    @SuppressLint("CheckResult")
    private void startTimer() {
        List<String> list = new ArrayList<>();
        for (int i = 100; i >= 0; i--) {
            list.add(i + "s");
        }
        Observable<String> observable = Observable.fromIterable(list);
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        Observable.zip(observable, interval, (s, aLong) -> s).subscribe(new CommonDisposable<String>(this) {
            @Override
            public void onNext(String s) {
                mTimer.postValue("跳过：" + s);
                L.i("跳过：" + s);
                if (TextUtils.equals("98s", s)) {
                    mActivitySkip.postValue(new ActivitySkip("mainActivity"));
                }
            }
        });
    }

    @Override
    protected SplashModel initModel() {
        return new SplashModel();
    }

    @Override
    protected void init() {
        startTimer();
        request();
//        mActivitySkip.postValue(new ActivitySkip("mainActivity"));
    }

    public MutableLiveData<ImageBean> getImage() {
        return mImage;
    }

    public void setImage(MutableLiveData<ImageBean> mImage) {
        this.mImage = mImage;
    }

    public MutableLiveData<String> getTimer() {
        return mTimer;
    }

    public void setTimer(MutableLiveData<String> mTimer) {
        this.mTimer = mTimer;
    }

    public MutableLiveData<ActivitySkip> getActivitySkip() {
        return mActivitySkip;
    }

    public void setActivitySkip(MutableLiveData<ActivitySkip> mActivitySkip) {
        this.mActivitySkip = mActivitySkip;
    }
}
