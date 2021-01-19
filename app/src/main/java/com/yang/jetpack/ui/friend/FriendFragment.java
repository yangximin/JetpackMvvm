package com.yang.jetpack.ui.friend;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yang.jetpack.R;
import com.yang.jetpack.adapter.FriendAdapter;
import com.yang.jetpack.base.BaseFragment;
import com.yang.jetpack.databinding.FragmentListLayoutBinding;

/**
 * Created by yxm on 2021/1/8.
 */
public class FriendFragment extends BaseFragment<FragmentListLayoutBinding, FriendViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_layout;
    }

    @Override
    public void init() {
        initRcyView();
    }

    @Override
    public FriendViewModel initViewModel() {
        return ViewModelProviders.of(this).get(FriendViewModel.class);
    }

    private void initRcyView() {
        mDataBinding.rcyMain.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mDataBinding.rcyMain.setAdapter(new FriendAdapter());

    }
}
