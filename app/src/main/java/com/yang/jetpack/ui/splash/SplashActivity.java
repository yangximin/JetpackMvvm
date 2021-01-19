package com.yang.jetpack.ui.splash;

import android.text.TextUtils;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.yang.jetpack.R;
import com.yang.jetpack.base.BaseActivity;
import com.yang.jetpack.bean.ActivitySkip;
import com.yang.jetpack.bean.ImageBean;
import com.yang.jetpack.databinding.ActivitySplashBinding;
import com.yang.jetpack.ui.MainActivity;

/**
 * Created by yxm on 2021/1/12.
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init() {
//        mViewModel.getTimer().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                mDataBinding.tvSplashTimer.setText(s);
//            }
//        });
        mDataBinding.setViewModel(mViewModel);
        mViewModel.getImage().observe(this, new Observer<ImageBean>() {
            @Override
            public void onChanged(ImageBean imageBean) {
                String url = imageBean.getImages().get(0).getBaseUrl()
                        + imageBean.getImages().get(0).getUrl();
                Glide.with(SplashActivity.this).load(url).into(mDataBinding.ivSplashImg);
            }
        });
        mViewModel.getActivitySkip().observe(this, new Observer<ActivitySkip>() {
            @Override
            public void onChanged(ActivitySkip activitySkip) {
                if (TextUtils.equals("mainActivity", activitySkip.getmActivity())) {
                    toActivity(MainActivity.class);
                    finish();
                }
            }
        });
    }

    @Override
    public SplashViewModel initViewModel() {
        return ViewModelProviders.of(this).get(SplashViewModel.class);
    }
}
