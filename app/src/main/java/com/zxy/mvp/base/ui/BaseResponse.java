package com.zxy.mvp.base.ui;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */

public class BaseResponse implements IDataProtocol {

    private long timestamp;
    private int ret;
    private String error;
    private String token;

    @Override
    public int getRet() {
        return ret;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public String getToken() {
        return token;
    }
}
