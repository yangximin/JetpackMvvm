package com.yang.jetpack.base.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yang.jetpack.base.model.BaseModel;
import com.yang.jetpack.config.LoadState;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by yxm on 2021/1/11.
 */
public abstract class BaseViewModel<M extends BaseModel> extends ViewModel implements DefaultLifecycleObserver {

    CompositeDisposable mDisposable;

    public M mModel;

    public MutableLiveData<LoadState> loadState;

    public BaseViewModel() {
        mModel = initModel();
        loadState = new MutableLiveData<>();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        init();
    }

    protected abstract M initModel();

    protected abstract void init();

    public void addDisposable(Disposable disposable) {
        mDisposable.add(disposable);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        mDisposable.clear();
    }
}
