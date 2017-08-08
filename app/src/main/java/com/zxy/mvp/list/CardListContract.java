package com.zxy.mvp.list;

import com.zxy.mvp.base.ui.IRxPresenter;
import com.zxy.mvp.base.ui.IRxView;
import com.zxy.mvp.data.entity.BankCardEntity;

import java.util.List;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/3
 */
public interface CardListContract {

    interface View extends IRxView {
        void showCardList(List<BankCardEntity> tasks);
    }

    interface Presenter extends IRxPresenter<View> {
        void getTaskList();

        void addNewTask(BankCardEntity task);

        void deleteTask(String taskId);

        void changeTaskStatus(String taskId);

        void deleteAllTasks();


    }
}
