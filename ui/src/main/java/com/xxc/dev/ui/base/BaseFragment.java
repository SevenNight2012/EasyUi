package com.xxc.dev.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * fragment 基类
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(layoutId(), container, false);
            initView(savedInstanceState);
        }
        return mRootView;
    }

    public abstract int layoutId();

    public abstract void initView(@Nullable Bundle savedInstanceState);

    public <V extends View> V findViewById(@IdRes int id) {
        return mRootView == null ? null : mRootView.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;
    }
}
