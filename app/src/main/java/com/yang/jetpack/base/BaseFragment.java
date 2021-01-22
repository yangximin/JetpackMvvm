package com.yang.jetpack.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.yang.jetpack.R;
import com.yang.jetpack.base.viewmodel.BaseViewModel;
import com.yang.jetpack.config.LoadState;
import com.yang.jetpack.databinding.FragmentBaseBinding;

/**
 * Created by yxm on 2021/1/11.
 */
public abstract class BaseFragment<DB extends ViewDataBinding, VM extends BaseViewModel<?>> extends Fragment {

    public DB mDataBinding;
    public VM mViewModel;
    private FragmentBaseBinding mFragmentBaseBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = initViewModel();
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false);
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), mFragmentBaseBinding.fgBaseContent, true);
        mDataBinding.setLifecycleOwner(this);
        init();
        initLoadState();
        return mFragmentBaseBinding.getRoot();
    }

    private void initLoadState() {
        if (mViewModel != null) {
            mViewModel.loadState.observe(getViewLifecycleOwner(), new Observer<LoadState>() {
                @Override
                public void onChanged(LoadState loadState) {
                    switchLoadState(loadState);
                }
            });
        }
    }

    private void switchLoadState(LoadState loadState) {
        removeLoadView();
        switch (loadState) {
            case ERROR:
                break;
            case NO_NETWORK:
                break;
            case SUCCESS:
//                mFragmentBaseBinding.fgBaseContent.addView(mDataBinding.getRoot());
                break;
            case LOADING:
                DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_loading_view, mFragmentBaseBinding.fgBaseContent, true);
                break;
            case NO_DATA:
                break;
        }
    }

    private void removeLoadView() {
        int childCount = mFragmentBaseBinding.fgBaseContent.getChildCount();
        if (childCount > 1) {
            mFragmentBaseBinding.fgBaseContent.removeViews(0, childCount - 1);
        }
    }

    public abstract int getLayoutId();

    public abstract void init();

    public abstract VM initViewModel();
}
