package com.zxy.mvp.data.source;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.data.entity.BankCardEntity;

import rx.Observable;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public interface BankCardDataSource {

    Observable<BaseListEntity<BankCardEntity>> addBankCard(BankCardEntity task);

    Observable<BaseListEntity<BankCardEntity>> deleteBankCard(String taskId);

    Observable<BaseListEntity<BankCardEntity>> getBankCardList();

    Observable<BaseListEntity<BankCardEntity>> chageBankCardStatus(String taskId);

    Observable<BaseListEntity<BankCardEntity>> delteAllBankCard();

    Observable<BankCardEntity> getBankCard(String taskId);


}
