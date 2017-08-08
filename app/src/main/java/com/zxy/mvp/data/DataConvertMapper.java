package com.zxy.mvp.data;

import com.zxy.mvp.data.entity.BaseEntity;
import com.zxy.mvp.base.ui.BaseResponse;
import com.zxy.mvp.data.entity.EmptyEntity;
import com.zxy.mvp.base.ui.IDataProtocol;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/1
 */
public class DataConvertMapper {

    /**
     * build local entity data protocal info.
     *
     * @param entity
     */
    public static void buildValidEntityInfo(BaseEntity entity) {
        if (entity == null) {
            return;
        }
        entity.setError("");
        entity.setRet(IDataProtocol.RET_OK);
    }
    /**
     * build BaseResponse -> BaseEntity.
     *
     * @param entity
     * @param response
     */
    private static void buildBaseInfo(BaseEntity entity, BaseResponse response) {
        if (entity == null || response == null) {
            return;
        }
        entity.setError(response.getError());
        entity.setRet(response.getRet());
        entity.setToken(response.getToken());
        entity.setTimestamp(response.getTimestamp());
    }

    /**
     * convert BaseResponse -> EmptyEntity.
     *
     * @param response
     * @return
     */
    public static EmptyEntity mapper(BaseResponse response) {
        EmptyEntity emptyEntity = new EmptyEntity();
        buildBaseInfo(emptyEntity, response);
        return emptyEntity;
    }
}
