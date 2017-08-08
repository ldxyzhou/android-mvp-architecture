package com.zxy.mvp.base;
import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.base.ui.IDataProtocol;

import java.util.List;


/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */

public abstract class ListUseCase<Q extends UseCase.RequestValues, P extends IDataProtocol> extends UseCase<Q, BaseListEntity<P>> {


    public void executeByList(Q values, final UseCaseCallback<List<P>> callback) {
        super.execute(values, new UseCaseCallback<BaseListEntity<P>>() {
            @Override
            public void onSuccess(BaseListEntity<P> response) {
                if (callback != null) {
                    callback.onSuccess(response == null ? null : response.list());
                }
            }

            @Override
            public void onError(int code, String error) {
                callback.onError(code, error);
            }
        });
    }

}
