package com.zxy.mvp.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.UUID;

/**
 * @author：xinyu.zhou
 * @version: 2017/7/29 14:09
 */
@Entity
public class BankCardEntity extends BaseEntity {

    @Id
    private  String id;

    private  String title;

    private  String description;

    private  boolean completed;



    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Generated(hash = 1661682348)
    public BankCardEntity(String id, String title, String description,
            boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }
    public BankCardEntity(String title, String description,
                          boolean completed) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    @Override
    public int getRet() {
        return 1;
    }
    @Generated(hash = 190104059)
    public BankCardEntity() {
    }




}
