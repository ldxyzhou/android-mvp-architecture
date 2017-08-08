package com.zxy.mvp.base;


import rx.Subscriber;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */
public abstract class ExceptionHandlerSubscriber<T> extends Subscriber<T> {

    private static final String TAG = "ExceptionHandlerSubscriber";

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onCompleted() {

    }
}
