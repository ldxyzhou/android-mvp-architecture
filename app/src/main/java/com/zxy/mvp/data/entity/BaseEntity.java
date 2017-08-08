package com.zxy.mvp.data.entity;

import com.zxy.mvp.base.ui.IDataProtocol;
import com.zxy.mvp.data.DataConvertMapper;

import java.io.Serializable;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */
public class BaseEntity implements IDataProtocol, Serializable {

    public BaseEntity() {
        this(false);
    }

    public BaseEntity(boolean isNotRemote) {
        if (isNotRemote) {
            DataConvertMapper.buildValidEntityInfo(this);
        }
    }

    private long timestamp;

    private int ret;

    private String error;

    private String token;

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    @Override
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
