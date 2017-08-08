package com.zxy.mvp.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.zxy.mvp.R;
import com.zxy.mvp.base.ui.BaseActivity;
import com.zxy.mvp.data.entity.BankCardEntity;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/4 10:18
 */
public class CardDetailActivity extends BaseActivity<CardDetailContract.Presenter> implements CardDetailContract.View {
    public static final String CARD_ID = "card_id";
    private Toolbar mToolbar;
    private BankCardEntity bankCardEntity;
    private EditText title;
    private EditText description;


    @Override
    protected CardDetailContract.Presenter createPresenter() {
        return new CardDetailPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detaile);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.context);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        if (getIntent() != null) {
            String id = getIntent().getStringExtra(CARD_ID);
            getPresenter().getBankCardDetail(id);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save:
                if (TextUtils.isEmpty(title.getText().toString())){
                    Toast.makeText(CardDetailActivity.this,"title is null",Toast.LENGTH_SHORT).show();
                }else{
                    if (bankCardEntity!=null){
                        bankCardEntity.setTitle(title.getText().toString());
                        bankCardEntity.setDescription(description.getText().toString());
                    }else{
                        bankCardEntity = new BankCardEntity(title.getText().toString(), description.getText().toString(), true);
                    }
                    getPresenter().saveBankCard(bankCardEntity);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_card_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSaveResult(boolean result) {
        if (result) {
            setResult(RESULT_OK);
            finish();
            Toast.makeText(CardDetailActivity.this, "save success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CardDetailActivity.this, "save fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDetailView(BankCardEntity entity) {
        if (entity!=null){
            bankCardEntity = entity;
            title.setText(bankCardEntity.getTitle());
            description.setText(bankCardEntity.getDescription());
        }
    }
}
