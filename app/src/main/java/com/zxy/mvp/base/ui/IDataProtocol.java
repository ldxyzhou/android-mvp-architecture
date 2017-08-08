package com.zxy.mvp.base.ui;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */
public interface IDataProtocol {

    int RET_OK = 1;

    int getRet();

    long getTimestamp();

    String getError();

    String getToken();
}
