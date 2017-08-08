package com.zxy.mvp.data.source;

import com.zxy.mvp.data.entity.BaseListEntity;
import com.zxy.mvp.data.entity.BankCardEntity;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class BankCardRepository implements BankCardDataSource {
    private static BankCardRepository INSTANCE;

    private BankCardDataSource mRemoteDataSource;
    private BankCardDataSource mLocalDataSource;

    public BankCardRepository(BankCardDataSource remoteDataSource, BankCardDataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static BankCardRepository getInstance(BankCardDataSource remoteDataSource, BankCardDataSource localDataSource) {
        if (INSTANCE == null) {
            BankCardRepository source = new BankCardRepository(remoteDataSource, localDataSource);
            INSTANCE = source;
        }
        return INSTANCE;
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> addBankCard(BankCardEntity task) {
        mRemoteDataSource.addBankCard(task).doOnNext(new Action1<BaseListEntity<BankCardEntity>>() {
            @Override
            public void call(BaseListEntity<BankCardEntity> taskEntityBaseListEntity) {
                //TODO: save data to local
                for (BankCardEntity entity : taskEntityBaseListEntity.list()) {
                    mLocalDataSource.addBankCard(entity);
                }
            }
        });
        return mLocalDataSource.addBankCard(task);
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> deleteBankCard(String taskId) {

        mRemoteDataSource.deleteBankCard(taskId).doOnNext(new Action1<BaseListEntity<BankCardEntity>>() {
            @Override
            public void call(BaseListEntity<BankCardEntity> taskEntityBaseListEntity) {
                //TODO: save data to local
                for (BankCardEntity entity : taskEntityBaseListEntity.list()) {
                    mLocalDataSource.addBankCard(entity);
                }
            }
        });
        return mLocalDataSource.deleteBankCard(taskId);
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> getBankCardList() {
        mRemoteDataSource.getBankCardList().doOnNext(new Action1<BaseListEntity<BankCardEntity>>() {
            @Override
            public void call(BaseListEntity<BankCardEntity> taskEntityBaseListEntity) {
                //TODO: save data to local
                for (BankCardEntity entity : taskEntityBaseListEntity.list()) {
                    mLocalDataSource.addBankCard(entity);
                }
            }
        });
        return mLocalDataSource.getBankCardList();
    }

    @Override
    public Observable<BankCardEntity> getBankCard(String cardId) {
        Observable<BankCardEntity> remote = mRemoteDataSource.getBankCard(cardId).doOnNext(new Action1<BankCardEntity>() {
            @Override
            public void call(BankCardEntity entity) {
                mLocalDataSource.addBankCard(entity);
            }
        });
        Observable<BankCardEntity> local = mLocalDataSource.getBankCard(cardId);
        return Observable.concat(remote, local).first();
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> delteAllBankCard() {
               mRemoteDataSource.delteAllBankCard();
        return mLocalDataSource.delteAllBankCard();
    }

    @Override
    public Observable<BaseListEntity<BankCardEntity>> chageBankCardStatus(String cardId) {
              mRemoteDataSource.chageBankCardStatus(cardId);
        return mLocalDataSource.chageBankCardStatus(cardId);
    }

}
