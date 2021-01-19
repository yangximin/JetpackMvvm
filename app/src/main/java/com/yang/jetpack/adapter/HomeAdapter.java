package com.yang.jetpack.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.yang.jetpack.adapter.holder.CommonViewHolder;
import com.yang.jetpack.bean.home.HomeDate;

import java.util.List;

/**
 * Created by yxm on 2021/1/18.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeDate> mList;

    public void HomeAdapter(List<HomeDate> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(viewType), parent, false);
        return new CommonViewHolder(inflate);
    }

    private int getLayoutId(int viewType) {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        HomeDate homeDate = mList.get(position);
        switch (homeDate.getType()) {
            case 0:

                break;
            case 1:
                break;
            case 2:
                break;
        }
        return super.getItemViewType(position);
    }
}
