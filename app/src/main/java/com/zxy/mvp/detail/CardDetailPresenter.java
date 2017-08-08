package com.zxy.mvp.detail;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.base.ui.BasePresenter;
import com.zxy.mvp.base.UseCase;
import com.zxy.mvp.data.entity.BankCardEntity;
import com.zxy.mvp.domain.AddCardTask;
import com.zxy.mvp.domain.GetCardTask;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/4 18:23
 */
public class CardDetailPresenter extends BasePresenter<CardDetailContract.View> implements CardDetailContract.Presenter {
    private AddCardTask addCardTask;
    private GetCardTask getCardTask;

    public CardDetailPresenter(CardDetailContract.View view) {
        super(view);
        addCardTask = new AddCardTask();
        getCardTask = new GetCardTask();
    }

    @Override
    public void getBankCardDetail(String cardId) {
        getCardTask.execute(new GetCardTask.RequestValues(cardId), new UseCase.UseCaseCallback<BankCardEntity>() {
            @Override
            public void onSuccess(BankCardEntity entity) {
                getView().showDetailView(entity);
            }

            @Override
            public void onError(int code, String error) {
            }
        });
    }

    @Override
    public void saveBankCard(BankCardEntity entity) {
        addCardTask.execute(new AddCardTask.RequestValues(entity), new UseCase.UseCaseCallback<BaseListEntity<BankCardEntity>>() {
            @Override
            public void onSuccess(BaseListEntity<BankCardEntity> response) {
                getView().onSaveResult(true);
            }

            @Override
            public void onError(int code, String error) {
                getView().onSaveResult(false);
            }
        });
    }
}
