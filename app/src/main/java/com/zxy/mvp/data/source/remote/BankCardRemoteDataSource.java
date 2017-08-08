package com.zxy.mvp.data.source.remote;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.data.db.BankCardDao;
import com.zxy.mvp.data.entity.BankCardEntity;
import com.zxy.mvp.data.source.BankCardDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class BankCardRemoteDataSource implements BankCardDataSource {

    private static BankCardRemoteDataSource INSTANCE;
    private static Map<String, BankCardEntity> REMOTE_DATA = new HashMap<>();

    public static BankCardRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            BankCardRemoteDataSource source = new BankCardRemoteDataSource();
            INSTANCE = source;
        }
        return INSTANCE;
    }

    static {
        //mock data
        if (BankCardDao.getTaskList() == null || BankCardDao.getTaskList().size() == 0) {
            for (int i = 0; i < 5; i++) {
                BankCardEntity temp = new BankCardEntity("card " + i, "" + i, true);
                BankCardDao.insertOrUpdate(temp);
            }
        }
        List<BankCardEntity> list = BankCardDao.getTaskList();
        for (BankCardEntity bankCardEntity : list) {
            REMOTE_DATA.put(bankCardEntity.getId(), bankCardEntity);
        }
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> addBankCard(BankCardEntity task) {
        REMOTE_DATA.put(task.getId(), task);
        return getBankCardList();
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> deleteBankCard(String taskId) {
        REMOTE_DATA.remove(taskId);
        return getBankCardList();
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> getBankCardList() {
        return Observable.create(new Observable.OnSubscribe<BaseListEntity<BankCardEntity>>() {
            @Override
            public void call(Subscriber<? super BaseListEntity<BankCardEntity>> subscriber) {
                BaseListEntity<BankCardEntity> listEntity = new BaseListEntity<>(new ArrayList<>(REMOTE_DATA.values()), true);
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
                subscriber.onNext(REMOTE_DATA.get(cardId));
                subscriber.onCompleted();

            }
        });
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> delteAllBankCard() {
        REMOTE_DATA.clear();
        return getBankCardList();
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> chageBankCardStatus(String taskId) {
        BankCardEntity temp = REMOTE_DATA.get(taskId);
        if (temp != null) {
            temp.setCompleted(!temp.getCompleted());
            REMOTE_DATA.put(temp.getId(), temp);
        }
        return getBankCardList();
    }
}