package com.jz.hui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by Han on 2019/3/19
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/yin13753884368
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (BaseActivity) getActivity();
        View view = inflater.inflate(getLayoutRes(), container, false);
        bind = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    /**
     * 获取资源文件
     *
     * @return
     */
    protected abstract int getLayoutRes();


    /**
     * 初始化数据
     */
    protected abstract void initData();

}
