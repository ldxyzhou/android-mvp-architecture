package com.zxy.mvp.data.entity;

import com.zxy.mvp.base.ui.IDataProtocol;

import java.util.List;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */
public class BaseListEntity<D extends IDataProtocol> extends BaseEntity {

    private List<D> mList;

    public BaseListEntity(List<D> list) {
       this(list, false);
    }

    public BaseListEntity(List<D> list, boolean isNotRemote) {
        super(isNotRemote);
        mList = list;
    }

    public List<D> list() {
        return mList;
    }

    public void add(D d) {
        mList.add(d);
    }

    public void addAll(List<D> datas) {
        if (datas != null) {
            mList.addAll(datas);
        }
    }
}
