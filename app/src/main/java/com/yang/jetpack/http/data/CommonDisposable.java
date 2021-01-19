package com.yang.jetpack.http.data;

import com.yang.jetpack.base.utils.L;
import com.yang.jetpack.base.viewmodel.BaseViewModel;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by yxm on 2021/1/14.
 */
public  class CommonDisposable<T> implements Observer<T> {

    WeakReference<BaseViewModel<?>> weakReference;

    public CommonDisposable(BaseViewModel<?> viewModel) {
        this.weakReference = new WeakReference<>(viewModel);
    }



    @Override
    public void onSubscribe(@NonNull Disposable d) {
        BaseViewModel<?> baseViewModel = weakReference.get();
        if (baseViewModel != null) {
            baseViewModel.addDisposable(d);
        }
    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        L.exception(e);
    }

    @Override
    public void onComplete() {

    }


}
