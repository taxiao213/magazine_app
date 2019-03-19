package com.jz.hui.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.os.StrictMode;
import android.support.annotation.NonNull;

import com.jz.hui.R;
import com.jz.hui.ui.view.smartrefreshlayout.api.DefaultRefreshFooterCreator;
import com.jz.hui.ui.view.smartrefreshlayout.api.DefaultRefreshHeaderCreator;
import com.jz.hui.ui.view.smartrefreshlayout.api.RefreshFooter;
import com.jz.hui.ui.view.smartrefreshlayout.api.RefreshHeader;
import com.jz.hui.ui.view.smartrefreshlayout.api.RefreshLayout;
import com.jz.hui.ui.view.smartrefreshlayout.footer.ClassicsFooter;
import com.jz.hui.ui.view.smartrefreshlayout.header.ClassicsHeader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import static com.jz.hui.ui.view.smartrefreshlayout.SmartRefreshLayout.setDefaultRefreshFooterCreator;
import static com.jz.hui.ui.view.smartrefreshlayout.SmartRefreshLayout.setDefaultRefreshHeaderCreator;


/**
 * 1.获取主线程 名称 id
 * 2.获取上下文
 * Created by Han on 2017/12/11
 * Email:yin13753884368@163.com
 * CSDN:http://blog.csdn.net/yin13753884368/article
 * Github:https://github.com/yin13753884368
 */

public class MyApplication extends Application {
    //获取上下文
    public static MyApplication mContext;
    //获取主线程 名称
    private static String mThreadName;
    //获取主线程 id
    private static long mTthreadId;
    //获取到主线程的handler
    private static Handler mMainThreadHandler = null;

    static {
        //设置全局的Header构建器
        setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.grey_df);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });

    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Thread thread = Thread.currentThread();
        mThreadName = thread.getName();
        mTthreadId = thread.getId();
        mMainThreadHandler = new Handler();

        //获取打开相机权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static MyApplication getMyApplication() {
        return mContext;
    }

    /**
     * 获取主线程 名称
     *
     * @return
     */
    public static String getMainThreadName() {
        return mThreadName;
    }

    /**
     * 获取主线程 id
     *
     * @return
     */
    public static long getMainThreadId() {
        return mTthreadId;
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取渠道id
     *
     * @return
     */
    private String getChannel() {
        if (mContext == null) {
            return null;
        }
        String channel = null;
        try {
            ApplicationInfo info = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            if (info != null && info.metaData != null) {
                channel = info.metaData.getString("UMENG_CHANNEL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channel;
    }

    /**
     * 获取当前进程的包名
     *
     * @return
     */
    public static String getProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
