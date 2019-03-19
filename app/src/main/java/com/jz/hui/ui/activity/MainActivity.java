package com.jz.hui.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jz.hui.base.BaseActivity;
import com.jz.hui.constant.Constant;
import com.jz.hui.ui.fragment.FragmentBookShelf;
import com.jz.hui.ui.fragment.FragmentClassification;
import com.jz.hui.ui.fragment.FragmentLike;
import com.jz.hui.ui.fragment.FragmentMy;
import com.jz.hui.R;

import butterknife.BindView;

import static com.jz.hui.constant.Constant.TAG_FRAGMENT1;
import static com.jz.hui.constant.Constant.TAG_FRAGMENT2;
import static com.jz.hui.constant.Constant.TAG_FRAGMENT3;
import static com.jz.hui.constant.Constant.TAG_FRAGMENT4;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.rb_homepage)
    RadioButton rbHomepage;
    @BindView(R.id.rb_homecreatetask)
    RadioButton rbHomecreatetask;
    @BindView(R.id.rb_homemanage)
    RadioButton rbHomemanage;
    @BindView(R.id.rb_homepeople)
    RadioButton rbHomepeople;
    @BindView(R.id.rg)
    RadioGroup rg;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void getData() {
        switchFragment(TAG_FRAGMENT1);
        rg.setOnCheckedChangeListener(this);
    }


    /**
     * 切换fragment
     *
     * @param tab
     */
    private void switchFragment(String tab) {
        switch (tab) {
            case TAG_FRAGMENT1:
                rg.check(R.id.rb_homepage);
                break;
            case TAG_FRAGMENT2:
                rg.check(R.id.rb_homecreatetask);
                break;
            case TAG_FRAGMENT3:
                rg.check(R.id.rb_homemanage);
                break;
            case TAG_FRAGMENT4:
                rg.check(R.id.rb_homepeople);
                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment1 = fragmentManager.findFragmentByTag(TAG_FRAGMENT1);
        Fragment fragment2 = fragmentManager.findFragmentByTag(TAG_FRAGMENT2);
        Fragment fragment3 = fragmentManager.findFragmentByTag(TAG_FRAGMENT3);
        Fragment fragment4 = fragmentManager.findFragmentByTag(TAG_FRAGMENT4);
        if (fragment1 != null) {
            transaction.hide(fragment1);
        }
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null) {
            transaction.hide(fragment3);
        }
        if (fragment4 != null) {
            transaction.hide(fragment4);
        }

        switch (i) {
            case R.id.rb_homepage:
                if (fragment1 == null) {
                    fragment1 = new FragmentLike();
                    transaction.add(R.id.fl, fragment1, TAG_FRAGMENT1);
                } else {
                    transaction.show(fragment1);
                }
                Constant.CURRENT_TAG = TAG_FRAGMENT1;
                break;
            case R.id.rb_homecreatetask:
                if (fragment2 == null) {
                    fragment2 = new FragmentClassification();
                    transaction.add(R.id.fl, fragment2, TAG_FRAGMENT2);
                } else {
                    transaction.show(fragment2);
                }
                Constant.CURRENT_TAG = TAG_FRAGMENT2;
                break;
            case R.id.rb_homemanage:
                if (fragment3 == null) {
                    fragment3 = new FragmentBookShelf();
                    transaction.add(R.id.fl, fragment3, TAG_FRAGMENT3);
                } else {
                    transaction.show(fragment3);
                }
                Constant.CURRENT_TAG = TAG_FRAGMENT3;
                break;
            case R.id.rb_homepeople:
                if (fragment4 == null) {
                    fragment4 = new FragmentMy();
                    transaction.add(R.id.fl, fragment4, TAG_FRAGMENT4);
                } else {
                    transaction.show(fragment4);
                }
                Constant.CURRENT_TAG = TAG_FRAGMENT4;
                break;
        }
        transaction.commitNowAllowingStateLoss();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (Constant.CURRENT_TAG != null) {
            outState.putString(Constant.CURRENT_TAG, Constant.CURRENT_TAG);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String tag = savedInstanceState.getString("current_tag");
            if (tag != null) {
                switchFragment(tag);
            }
        }
    }
}
