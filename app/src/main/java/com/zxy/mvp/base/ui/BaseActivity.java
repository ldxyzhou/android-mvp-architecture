package com.zxy.mvp.base.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/3 11:30
 */
public class BaseActivity<P extends IRxPresenter> extends AppCompatActivity {

    private P mPresenter;
    private CompositeSubscription mSimpleCompositeSubscription = new CompositeSubscription();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    protected P createPresenter() {
        return null;
    }

    protected P getPresenter() {
        return mPresenter;
    }

    protected void addSubscription(Subscription s) {
        mSimpleCompositeSubscription.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
        if (mSimpleCompositeSubscription.hasSubscriptions()) {
            mSimpleCompositeSubscription.clear();
        }
    }
}
