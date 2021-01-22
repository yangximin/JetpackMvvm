package com.yang.jetpack.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.yang.jetpack.BR;
import com.yang.jetpack.R;
import com.yang.jetpack.adapter.holder.CommonViewHolder;
import com.yang.jetpack.bean.home.HomeDate;

import java.util.List;

/**
 * Created by yxm on 2021/1/18.
 */
public class HomeAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private List<HomeDate> mList;

    public HomeAdapter(List<HomeDate> list) {
        mList = list;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(viewType), parent, false);
        return new CommonViewHolder(inflate);
    }

    private int getLayoutId(int viewType) {
        switch (viewType) {
            case 0:
                return R.layout.item_home_banner;
            default:
                return R.layout.item_article;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        HomeDate homeDate = mList.get(position);
        if (itemViewType == 0) {
            holder.bindingUtil.setVariable(BR.bannerData,homeDate.getObject());
        } else if (itemViewType == 1) {
            holder.bindingUtil.setVariable(BR.articleBean,homeDate.getObject());
        } else if (itemViewType == 2) {
            holder.bindingUtil.setVariable(BR.articleBean,homeDate.getObject());
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        HomeDate homeDate = mList.get(position);
        return homeDate.getType();
    }
}
