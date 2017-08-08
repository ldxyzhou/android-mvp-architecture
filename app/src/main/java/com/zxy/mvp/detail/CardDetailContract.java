package com.zxy.mvp.detail;

import com.zxy.mvp.base.ui.IRxPresenter;
import com.zxy.mvp.base.ui.IRxView;
import com.zxy.mvp.data.entity.BankCardEntity;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/4 18:18
 */
public interface CardDetailContract {


    interface View extends IRxView {
        void showDetailView(BankCardEntity entity);
        void onSaveResult(boolean result);
    }

    interface Presenter extends IRxPresenter<View> {
        void getBankCardDetail(String cardId);
        void saveBankCard(BankCardEntity entity);
    }
}
