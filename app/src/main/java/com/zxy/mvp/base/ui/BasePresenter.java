package com.zxy.mvp.base.ui;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */
public class BasePresenter<V extends IRxView> implements IRxPresenter<V> {

    private Map<String, Subscription> mSubscriptions = new HashMap<>();

    private V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public void addSubscription(Subscription subscription) {
        addSubscription(String.valueOf(subscription.hashCode()), subscription);
    }

    @Override
    public void addSubscription(String tag, Subscription subscription) {
        mSubscriptions.put(tag, subscription);
    }

    @Override
    public void cancelSubscription(String tag) {
        if (mSubscriptions.containsKey(tag)) {
            mSubscriptions.get(tag).unsubscribe();
        }
    }

    @Override
    public void cancelAll() {
        for (Subscription subscription : mSubscriptions.values()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void destroy() {
        cancelAll();
        mSubscriptions = null;
    }

}
