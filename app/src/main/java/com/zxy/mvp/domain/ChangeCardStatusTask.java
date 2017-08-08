package com.zxy.mvp.domain;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.base.UseCase;
import com.zxy.mvp.data.entity.BankCardEntity;

import rx.Observable;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class ChangeCardStatusTask extends UseCase<ChangeCardStatusTask.RequestValues, BaseListEntity<BankCardEntity>> {

    @Override
    protected Observable<BaseListEntity<BankCardEntity>> buildUseCase(ChangeCardStatusTask.RequestValues requestValues) {
        return RepositoryProvider.getTasksRepository().chageBankCardStatus(requestValues.taskId);
    }

    public static class RequestValues extends UseCase.BaseRequestValues {
        public String taskId;

        public RequestValues(String id) {
            this.taskId = id;
        }
    }

}
