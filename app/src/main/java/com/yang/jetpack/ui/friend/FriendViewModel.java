package com.yang.jetpack.ui.friend;

import androidx.lifecycle.MutableLiveData;

import com.yang.jetpack.base.viewmodel.BaseViewModel;

/**
 * Created by yxm on 2021/1/19.
 */
public class FriendViewModel extends BaseViewModel<FriendModel> {

    MutableLiveData<String> count;

    @Override
    protected FriendModel initModel() {
        return new FriendModel();
    }

    @Override
    protected void init() {
        count = new MutableLiveData<>();
    }

    public MutableLiveData<String> getCount() {
        return count;
    }

    public void setCount(MutableLiveData<String> count) {
        this.count = count;
    }
}
