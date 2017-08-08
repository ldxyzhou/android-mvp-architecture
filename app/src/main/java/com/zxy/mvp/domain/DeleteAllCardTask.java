package com.zxy.mvp.domain;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.base.UseCase;
import com.zxy.mvp.data.entity.BankCardEntity;

import rx.Observable;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class DeleteAllCardTask extends UseCase<UseCase.EmptyRequestValues, BaseListEntity<BankCardEntity>> {

    @Override
    protected Observable<BaseListEntity<BankCardEntity>> buildUseCase(UseCase.EmptyRequestValues emptyRequestValues) {
        return RepositoryProvider.getTasksRepository().delteAllBankCard();
    }


}
