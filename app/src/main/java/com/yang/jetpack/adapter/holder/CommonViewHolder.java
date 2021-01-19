package com.yang.jetpack.adapter.holder;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by yxm on 2021/1/18.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {

    ViewDataBinding bindingUtil;

    public CommonViewHolder(ViewDataBinding bindingUtil) {
        super(bindingUtil.getRoot());
        this.bindingUtil = bindingUtil;
    }
}
