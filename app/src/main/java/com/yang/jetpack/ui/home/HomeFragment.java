package com.yang.jetpack.ui.home;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yang.jetpack.R;
import com.yang.jetpack.base.BaseFragment;
import com.yang.jetpack.databinding.FragmentListLayoutBinding;

/**
 * Created by yxm on 2021/1/8.
 */
public class HomeFragment extends BaseFragment<FragmentListLayoutBinding, HomeViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_layout;
    }

    @Override
    public void init() {
        initRcyView();
    }

    @Override
    public HomeViewModel initViewModel() {
        return ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    private void initRcyView() {
        mDataBinding.rcyMain.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
