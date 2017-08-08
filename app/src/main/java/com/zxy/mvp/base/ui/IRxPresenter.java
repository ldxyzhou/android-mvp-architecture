package com.zxy.mvp.base.ui;

import rx.Subscription;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */
public interface IRxPresenter <V extends IRxView> {

    V getView();

    void addSubscription(Subscription subscription);

    void addSubscription(String tag, Subscription subscription);

    void cancelSubscription(String tag);

    void cancelAll();

    void destroy();
}
