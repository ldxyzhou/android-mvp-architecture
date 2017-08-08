package com.zxy.mvp.base;

import com.zxy.mvp.base.ui.ApplySchedulers;
import com.zxy.mvp.base.ui.IDataProtocol;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/17
 */
public abstract class UseCase<Q extends UseCase.RequestValues, P extends IDataProtocol> {

    private boolean mStopAllWithFuture;

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    void run() {
        Observable<P> task = buildUseCase(mRequestValues);
        if (task == null) {
            return;
        }
        mCompositeSubscription.add(task.compose(new ApplySchedulers<P>())
                       .subscribe(new EntitySubscriber<>(mUseCaseCallback)));
    }

    protected abstract Observable<P> buildUseCase(Q requestValues);

    public final void execute(Q values, UseCase.UseCaseCallback<P> callback) {
        if (mStopAllWithFuture) {
            return;
        }
        setRequestValues(values);
        setUseCaseCallback(callback);
        run();
    }

    /**
     * Unsubscribes from current {@link Subscription}.
     */
    public void cancel() {
        cancel(true);
    }


    /**
     * Unsubscribes from current {@link Subscription}.
     *
     * @param stopAllWithFuture stop the future tasks.
     */
    public void cancel(boolean stopAllWithFuture) {
        mStopAllWithFuture = stopAllWithFuture;
        mCompositeSubscription.clear();
    }

    /**
     * empty request.
     */
    public static class EmptyRequestValues extends BaseRequestValues {

    }

    /**
     * base request.
     */
    public abstract static class BaseRequestValues implements RequestValues {

    }

    /**
     * Data passed to a request.
     */
    public interface RequestValues {

    }

    public interface UseCaseCallback<R> {

        void onSuccess(R response);

        void onError(int code, String error);
    }
}
