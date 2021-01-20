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

/**
 * Created by yxm on 2021/1/19.
 */
public class FriendAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_friend_layout, parent, false);
        return new CommonViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        holder.bindingUtil.setVariable(BR.countText, position + "");
    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
