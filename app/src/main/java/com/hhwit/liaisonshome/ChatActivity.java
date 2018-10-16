package com.hhwit.liaisonshome;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hongwen Huang on 2018/10/15
 * Email: hhwit@126.com
 */

public class ChatActivity extends Activity {

    private RecyclerView mListView;
    private ChatAdapter mAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mListView = findViewById(R.id.chat_list);

        mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(mManager);

        mAdapter = new ChatAdapter(this);
        mListView.setAdapter(mAdapter);

        initActions();

        List<ChatBean> mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ChatBean bean = null;
            if ((i % 3) + 1 == 1)
                bean = new ChatBean("China's foreign trade registered steady growth in the first eight months of this year despite higher tariffs imposed by the United States. " + Math.random());
            if ((i % 3) + 1 == 2)
                bean = new ChatBean("China's");
            if ((i % 3) + 1 == 3)
                bean = new ChatBean("Trade registered steady growth");
            bean.setType((i % 3) + 1);
            mList.add(bean);
        }
        mAdapter.addData(mList);

    }

    private void initActions() {
        mListView.addOnScrollListener(listListener);
        mAdapter.setCallback(listCallback);
    }

    private RecyclerView.OnScrollListener listListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        }
    };

    private ListViewCallback<ChatBean> listCallback = new ListViewCallback<ChatBean>() {
        @Override
        public void OnItemClickListener(int position, ChatBean bean, int flag) {
        }

        @Override
        public void OnItemLongClickListener(int position, ChatBean bean, int flag) {
        }
    };

}
