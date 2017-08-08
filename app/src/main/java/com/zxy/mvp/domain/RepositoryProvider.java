package com.zxy.mvp.domain;

import com.zxy.mvp.data.source.BankCardRepository;
import com.zxy.mvp.data.source.local.BankCardLocalDataSource;
import com.zxy.mvp.data.source.remote.BankCardRemoteDataSource;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class RepositoryProvider {

    public static BankCardRepository getTasksRepository() {
        return BankCardRepository.getInstance(BankCardRemoteDataSource.getInstance(),
                       BankCardLocalDataSource.getInstance());
    }

}
