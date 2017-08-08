package com.zxy.mvp.data.source.local;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.data.db.BankCardDao;
import com.zxy.mvp.data.entity.BankCardEntity;
import com.zxy.mvp.data.source.BankCardDataSource;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class BankCardLocalDataSource implements BankCardDataSource {

    private static BankCardLocalDataSource INSTANCE;

    public static BankCardLocalDataSource getInstance() {
        if (INSTANCE == null) {
            BankCardLocalDataSource source = new BankCardLocalDataSource();
            INSTANCE = source;
        }
        return INSTANCE;
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> addBankCard(BankCardEntity task) {
        BankCardDao.insertOrUpdate(task);
        return getBankCardList();
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> deleteBankCard(String taskId) {
        BankCardDao.delete(taskId);
        return getBankCardList();
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> getBankCardList() {
        return Observable.create(new Observable.OnSubscribe<BaseListEntity<BankCardEntity>>() {
            @Override
            public void call(Subscriber<? super BaseListEntity<BankCardEntity>> subscriber) {
                List<BankCardEntity> list = BankCardDao.getTaskList();
                BaseListEntity<BankCardEntity> listEntity = new BaseListEntity<>(list, true);
                subscriber.onNext(listEntity);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<BankCardEntity> getBankCard(final String cardId) {
        return Observable.create(new Observable.OnSubscribe<BankCardEntity>() {
            @Override
            public void call(Subscriber<? super BankCardEntity> subscriber) {
                BankCardEntity temp = BankCardDao.getBrankCardEntity(cardId);
                subscriber.onNext(temp);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> delteAllBankCard() {
        BankCardDao.deleteAll();
        return getBankCardList();
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> chageBankCardStatus(String cardId) {
        BankCardEntity temp = BankCardDao.getBrankCardEntity(cardId);
        if (temp != null) {
            temp.setCompleted(!temp.getCompleted());
            BankCardDao.insertOrUpdate(temp);
        }
        return getBankCardList();
    }
}
