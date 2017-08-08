package com.zxy.mvp.data.db;

import android.content.Context;
import android.util.Log;

import com.zxy.mvp.DaoMaster;
import com.zxy.mvp.DaoSession;
/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class DaoManager {

    private static final String DB_NAME = "mvp_db";
    private static DaoManager instance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;

    private DaoManager(Context context) {
        devOpenHelper=new DaoMaster.DevOpenHelper(context,DB_NAME,null);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
    }

    public static synchronized void init(Context context) {
        if (instance == null) {
            DaoManager manager = new DaoManager(context);
            instance = manager;
        }
    }

    public static DaoManager getInstance() {
        if (instance == null) {
            Log.e(DaoManager.class.getName(),"you should call initData first.");
        }
        return instance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
