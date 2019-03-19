package com.jz.hui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 * Created by Han on 2017/12/13
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/yin13753884368
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected BaseActivity mActivity;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        bind = ButterKnife.bind(this);
        this.mActivity = this;
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getData();
    }

    /**
     * 获取资源文件
     */
    protected abstract int getLayoutRes();

    /**
     * 获取数据
     */
    protected abstract void getData();

}
