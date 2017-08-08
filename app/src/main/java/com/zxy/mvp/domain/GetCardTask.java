package com.zxy.mvp.domain;

import com.zxy.mvp.base.UseCase;
import com.zxy.mvp.data.entity.BankCardEntity;

import rx.Observable;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/4 18:29
 */
public class GetCardTask extends UseCase<GetCardTask.RequestValues, BankCardEntity> {

    @Override
    protected Observable<BankCardEntity> buildUseCase(GetCardTask.RequestValues requestValues) {
        return RepositoryProvider.getTasksRepository().getBankCard(requestValues.cardId);
    }
    public static class RequestValues extends UseCase.BaseRequestValues {
        public String cardId;

        public RequestValues(String id) {
            this.cardId = id;
        }
    }
}
