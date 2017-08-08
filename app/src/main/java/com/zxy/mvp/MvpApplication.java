package com.zxy.mvp;

import android.app.Application;

import com.zxy.mvp.data.db.DaoManager;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/3
 */
public class MvpApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DaoManager.init(this);
    }
}
