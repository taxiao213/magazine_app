package com.jz.hui.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jz.hui.R;


/**
 * Created by Administrator on 2018/8/29.
 */

public class XSMyDownView extends RelativeLayout {

    private String name;
    private ImageView iv;
    private TextView tv;
    private int resourceId;

    public XSMyDownView(Context context) {
        super(context);
        initTypedArray(context, null);
        initView(context);
    }

    public XSMyDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypedArray(context, attrs);
        initView(context);
    }

    public XSMyDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypedArray(context, attrs);
        initView(context);
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.XSMyDownView);
        resourceId = mTypedArray.getResourceId(R.styleable.XSMyDownView_xs_image, R.drawable.xs_my_share);
        name = mTypedArray.getString(R.styleable.XSMyDownView_xs_text_name);
        //获取资源后要及时回收
        mTypedArray.recycle();
    }

    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.xs_my_down_view_layout, this, true);
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        tv.setText(name);
        if (resourceId != -1) {
            iv.setImageResource(resourceId);
        }
    }

}
