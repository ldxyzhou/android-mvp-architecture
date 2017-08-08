package com.zxy.mvp.domain;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.base.UseCase;
import com.zxy.mvp.data.entity.BankCardEntity;

import rx.Observable;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class AddCardTask extends UseCase<AddCardTask.RequestValues, BaseListEntity<BankCardEntity>> {

    @Override
    protected Observable<BaseListEntity<BankCardEntity>> buildUseCase(AddCardTask.RequestValues requestValues) {
        return RepositoryProvider.getTasksRepository().addBankCard(requestValues.bankCardEntity);
    }

    public static class RequestValues extends UseCase.BaseRequestValues {
        public BankCardEntity bankCardEntity;

        public RequestValues(BankCardEntity entity) {
            this.bankCardEntity = entity;
        }
    }

}
