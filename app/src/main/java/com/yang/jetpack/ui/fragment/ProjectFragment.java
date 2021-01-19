package com.yang.jetpack.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.yang.jetpack.R;
import com.yang.jetpack.databinding.FgProjectBinding;

/**
 * Created by yxm on 2021/1/8.
 */
public class ProjectFragment extends Fragment {
    FgProjectBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fg_project, container, false);
        return binding.getRoot();
    }
}
