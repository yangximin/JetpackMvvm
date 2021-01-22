package com.yang.jetpack.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yang.jetpack.R;
import com.yang.jetpack.adapter.HomeAdapter;
import com.yang.jetpack.base.BaseFragment;
import com.yang.jetpack.bean.home.HomeDate;
import com.yang.jetpack.databinding.FragmentListLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxm on 2021/1/8.
 */
public class HomeFragment extends BaseFragment<FragmentListLayoutBinding, HomeViewModel> {
    List<HomeDate> mHomeDates;
    private HomeAdapter mHomeAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_layout;
    }

    @Override
    public void init() {
        mDataBinding.setViewModel(mViewModel);
        initRcyView();
        mViewModel.mHomeDateList.observe(this, new Observer<List<HomeDate>>() {
            @Override
            public void onChanged(List<HomeDate> homeDates) {
                mHomeDates.addAll(homeDates);
                mHomeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public HomeViewModel initViewModel() {
        return new ViewModelProvider(this).get(HomeViewModel.class);
    }

    private void initRcyView() {
        mHomeDates = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(mHomeDates);
        mDataBinding.rcyMain.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.rcyMain.setAdapter(mHomeAdapter);

    }
}
