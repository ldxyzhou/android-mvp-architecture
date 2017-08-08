package com.zxy.mvp.data.db;

import android.text.TextUtils;
import android.util.Log;

import com.zxy.mvp.BankCardEntityDao;
import com.zxy.mvp.data.entity.BankCardEntity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class BankCardDao {

    private static BankCardEntityDao getBankCardDao() {
        return DaoManager.getInstance().getNewSession().getBankCardEntityDao();
    }

    public static synchronized void insertOrUpdate(BankCardEntity bean) {
        BankCardEntity bankCardEntity = getBrankCardEntity(bean.getId());
        if (bankCardEntity == null ) {
            getBankCardDao().insert(bean);
            Log.d("BankCardDao","insert");
        } else {
            bean.setId(bankCardEntity.getId());
            getBankCardDao().update(bean);

            Log.d("BankCardDao","update");
        }
    }

    public static BankCardEntity getBrankCardEntity(String cardId){
        if (cardId==null) return null;
        QueryBuilder<BankCardEntity> taskEntityQueryBuilder =getBankCardDao().queryBuilder().where(BankCardEntityDao.Properties.Id.eq(cardId));
        if (taskEntityQueryBuilder.list()!=null&&taskEntityQueryBuilder.list().size()>0){
            return taskEntityQueryBuilder.list().get(0);
        }
        return null;

    }

    public static synchronized void delete(String cardId) {
        BankCardEntity bankCardEntity = getBrankCardEntity(cardId );
        if (bankCardEntity !=null){
            getBankCardDao().delete(bankCardEntity);
        }

    }

    public static  void deleteAll() {
        getBankCardDao().deleteAll();

    }
    public static synchronized List<BankCardEntity> getTaskList( ) {
        return getBankCardDao().queryBuilder().list();
    }

}
