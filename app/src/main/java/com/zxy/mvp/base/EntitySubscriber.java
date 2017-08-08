package com.zxy.mvp.base;

import com.zxy.mvp.base.ui.IDataProtocol;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */
public class EntitySubscriber<T extends IDataProtocol> extends ExceptionHandlerSubscriber<T> {

    // call back.
    private UseCase.UseCaseCallback<T> mUseCaseCallback;

    public EntitySubscriber(UseCase.UseCaseCallback<T> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);

    }

    private void sendErrorEvent(int errCode, String message) {
        if (mUseCaseCallback != null) {
            mUseCaseCallback.onError(errCode, message);
        }
    }

    private void sendSuccessEvent(T t) {
        if (mUseCaseCallback != null) {
            mUseCaseCallback.onSuccess(t);
        }
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        if (t.getRet() == IDataProtocol.RET_OK) {
            sendSuccessEvent(t);
        } else {
            sendErrorEvent(t.getRet(), t.getError());
        }
    }
}
