package com.yang.jetpack.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;

import com.yang.jetpack.R;
import com.yang.jetpack.base.viewmodel.BaseViewModel;
import com.yang.jetpack.databinding.ActivityBaseBinding;

/**
 * Created by yxm on 2021/1/11.
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel<?>> extends AppCompatActivity {

    public DB mDataBinding;
    public VM mViewModel;
    private ActivityBaseBinding activityBaseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), activityBaseBinding.flActivityContent, true);
        mViewModel = initViewModel();
        init();
        mDataBinding.setLifecycleOwner(this);
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
    }

   protected void toActivity(Class<?> targetClass) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }

    public abstract int getLayoutId();

    public abstract void init();

    public abstract VM initViewModel();
}
