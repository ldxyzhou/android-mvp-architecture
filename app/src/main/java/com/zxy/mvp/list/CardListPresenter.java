package com.zxy.mvp.list;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.base.ui.BasePresenter;
import com.zxy.mvp.base.UseCase;
import com.zxy.mvp.data.entity.BankCardEntity;
import com.zxy.mvp.domain.AddCardTask;
import com.zxy.mvp.domain.ChangeCardStatusTask;
import com.zxy.mvp.domain.DeleteAllCardTask;
import com.zxy.mvp.domain.DeleteCardTask;
import com.zxy.mvp.domain.GetCardListTask;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/3
 */
public class CardListPresenter extends BasePresenter<CardListContract.View> implements CardListContract.Presenter {
    private GetCardListTask getCardListTask;
    private DeleteCardTask deleteCardTask;
    private AddCardTask addCardTask;
    private DeleteAllCardTask deleteAllCardTask;
    private ChangeCardStatusTask changeCardStatusTask;

    public CardListPresenter(CardListContract.View view) {
        super(view);
        getCardListTask = new GetCardListTask();
        deleteCardTask = new DeleteCardTask();
        addCardTask = new AddCardTask();
        deleteAllCardTask = new DeleteAllCardTask();
        changeCardStatusTask = new ChangeCardStatusTask();
    }

    @Override
    public void getTaskList() {

        getCardListTask.execute(new UseCase.EmptyRequestValues(), new UseCase.UseCaseCallback<BaseListEntity<BankCardEntity>>() {
            @Override
            public void onSuccess(BaseListEntity<BankCardEntity> response) {
                if (response != null) {
                    getView().showCardList(response.list());
                }
            }

            @Override
            public void onError(int code, String error) {

            }
        });
    }

    @Override
    public void addNewTask(BankCardEntity task) {
        addCardTask.execute(new AddCardTask.RequestValues(task), new UseCase.UseCaseCallback<BaseListEntity<BankCardEntity>>() {
            @Override
            public void onSuccess(BaseListEntity<BankCardEntity> response) {
                if (response != null) {
                    getView().showCardList(response.list());
                }
            }

            @Override
            public void onError(int code, String error) {

            }
        });
    }

    @Override
    public void deleteTask(String taskId) {
        deleteCardTask.execute(new DeleteCardTask.RequestValues(taskId), new UseCase.UseCaseCallback<BaseListEntity<BankCardEntity>>() {
            @Override
            public void onSuccess(BaseListEntity<BankCardEntity> response) {
                if (response != null) {
                    getView().showCardList(response.list());
                }
            }

            @Override
            public void onError(int code, String error) {

            }
        });

    }

    @Override
    public void changeTaskStatus(String taskId) {

        changeCardStatusTask.execute(new ChangeCardStatusTask.RequestValues(taskId), new UseCase.UseCaseCallback<BaseListEntity<BankCardEntity>>() {
            @Override
            public void onSuccess(BaseListEntity<BankCardEntity> response) {
                if (response != null) {
                    getView().showCardList(response.list());
                }
            }

            @Override
            public void onError(int code, String error) {
            }
        });
    }

    @Override
    public void deleteAllTasks() {
        deleteAllCardTask.execute(new UseCase.EmptyRequestValues(), new UseCase.UseCaseCallback<BaseListEntity<BankCardEntity>>() {
            @Override
            public void onSuccess(BaseListEntity<BankCardEntity> response) {
                if (response != null) {
                    getView().showCardList(response.list());
                }
            }

            @Override
            public void onError(int code, String error) {

            }
        });
    }

    @Override
    public void destroy() {
        super.destroy();
        getCardListTask.cancel();
        deleteCardTask.cancel();
        addCardTask.cancel();
        deleteAllCardTask.cancel();
        changeCardStatusTask.cancel();
    }
}
