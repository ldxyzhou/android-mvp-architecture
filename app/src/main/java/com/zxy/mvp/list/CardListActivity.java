package com.zxy.mvp.list;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zxy.mvp.R;
import com.zxy.mvp.base.ui.BaseActivity;
import com.zxy.mvp.data.entity.BankCardEntity;
import com.zxy.mvp.detail.CardDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：xinyu.zhou
 * @version: 2017/8/3
 */
public class CardListActivity extends BaseActivity<CardListContract.Presenter> implements SwipeRefreshLayout.OnRefreshListener, CardListContract.View {
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private TasksAdapter mAdapter;
    private List<BankCardEntity> bankCardList = new ArrayList<>();
    private Toolbar mToolbar;
    private static final int REQUEST_DETAIL = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Card List");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.inflateMenu(R.menu.base_toolbar_menu);
        mListView = (ListView) findViewById(R.id.listview);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_ly);
        mSwipeLayout.setColorSchemeColors(Color.parseColor("#FFA500"), Color.parseColor("#FFA500"));
        mSwipeLayout.setOnRefreshListener(this);
        registerForContextMenu(mListView);
        mAdapter = new TasksAdapter(bankCardList);
        mListView.setAdapter(mAdapter);


        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_task:
                        Intent intent = new Intent(CardListActivity.this, CardDetailActivity.class);
                        startActivityForResult(intent, REQUEST_DETAIL);
                        break;
                    case R.id.clear_task:
                        Toast.makeText(CardListActivity.this, "clear_task", Toast.LENGTH_SHORT).show();
                        getPresenter().deleteAllTasks();
                        break;
                }
                return true;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CardListActivity.this, CardDetailActivity.class);
                intent.putExtra(CardDetailActivity.CARD_ID, bankCardList.get(position).getId());
                startActivityForResult(intent, REQUEST_DETAIL);
            }
        });

        getPresenter().getTaskList();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DETAIL && resultCode == Activity.RESULT_OK) {
            getPresenter().getTaskList();
        }
    }

    @Override
    protected CardListContract.Presenter createPresenter() {
        return new CardListPresenter(this);
    }

    @Override
    public void onRefresh() {
        getPresenter().getTaskList();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, Menu.FIRST, 0, "completed");
        menu.add(0, Menu.FIRST + 1, 0, "pending");
        menu.add(0, Menu.FIRST + 2, 0, "delete");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo temp = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 1:
                if (!bankCardList.get(temp.position).getCompleted()) {
                    getPresenter().changeTaskStatus(bankCardList.get(temp.position).getId());
                }
                break;
            case 2:
                if (bankCardList.get(temp.position).getCompleted()) {
                    getPresenter().changeTaskStatus(bankCardList.get(temp.position).getId());
                }
                break;
            case 3:
                getPresenter().deleteTask(bankCardList.get(temp.position).getId());
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void showCardList(final List<BankCardEntity> tasks) {
        bankCardList.clear();
        bankCardList.addAll(tasks);
        mAdapter.setDataList(bankCardList);
        mAdapter.notifyDataSetChanged();
        mSwipeLayout.setRefreshing(false);
    }

    private static class TasksAdapter extends BaseAdapter {

        private List<BankCardEntity> mTaskList;

        public TasksAdapter(List<BankCardEntity> tasks) {
            setDataList(tasks);
        }

        private void setDataList(List<BankCardEntity> tasks) {
            mTaskList = tasks;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mTaskList == null ? 0 : mTaskList.size();
        }

        @Override
        public Object getItem(int position) {
            return mTaskList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                convertView = inflater.inflate(R.layout.card_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.status = (TextView) convertView.findViewById(R.id.status);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            BankCardEntity task = mTaskList.get(position);
            viewHolder.title.setText(task.getTitle());
            if (task.getCompleted()) {
                viewHolder.status.setText("completed");
            } else {
                viewHolder.status.setText("pending");
            }
            return convertView;
        }

        public static class ViewHolder {
            public TextView title;
            public TextView status;
        }
    }


}
