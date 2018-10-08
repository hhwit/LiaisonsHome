package com.hhwit.liaisonshome;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Hongwen Huang on 2018/10/8
 * Email: hhwit@126.com
 */

public class DiscoverFragment extends Fragment {
    public static final int UI_FOOTER_CALLBACK = 0;

    private SwipeRefreshLayout mSwipe;
    private RecyclerView mListView;
    private DiscoverAdapter mAdapter;
    private LinearLayoutManager mManager;

    public DiscoverFragment() {
    }

    public Handler uiHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case UI_FOOTER_CALLBACK:
                    mAdapter.setFooterState(DiscoverAdapter.FOOTER_STATE_GONE);
                    List<DiscoverBean> mList = new ArrayList<>();
                    for (int i = 0;i< 10; i++) {
                        mList.add(new DiscoverBean("China's foreign trade registered steady growth in the first eight months of this year despite higher tariffs imposed by the United States. " + Math.random(), "" + i));
                    }
                    mAdapter.addData(mList);
                    break;
            }
            return false;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = getView().findViewById(R.id.fragment_discover_view);
        mSwipe = getView().findViewById(R.id.fragment_discover_swipe);
        mSwipe.setColorSchemeResources(R.color.colorPrimaryDark);

        mManager = new LinearLayoutManager(getContext());
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(mManager);

        mAdapter = new DiscoverAdapter(getActivity());
        mListView.setAdapter(mAdapter);

        initActions();
    }

    private void initActions() {
        mSwipe.setOnRefreshListener(swipeListener);
        mListView.addOnScrollListener(listListener);
        mAdapter.setCallback(listCallback);
    }

    private SwipeRefreshLayout.OnRefreshListener swipeListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mAdapter.clearData();
            List<DiscoverBean> mList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                mList.add(new DiscoverBean("China's foreign trade registered steady growth in the first eight months of this year despite higher tariffs imposed by the United States. " + Math.random(), "" + i));
            }
            mAdapter.addData(mList);
            mSwipe.setRefreshing(false);
        }
    };

    private OnScrollListener listListener = new OnScrollListener() {
        int lastItem = 0;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            lastItem = mManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (lastItem == mAdapter.getItemCount() - 1) {
                mAdapter.setFooterState(DiscoverAdapter.FOOTER_STATE_LOADING);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        uiHandler.sendEmptyMessage(UI_FOOTER_CALLBACK);
                    }
                });
                thread.start();

            }
        }
    };

    private ListViewCallback<DiscoverBean> listCallback = new ListViewCallback<DiscoverBean>() {
        @Override
        public void OnItemClickListener(int position, DiscoverBean bean, int flag) {

        }

        @Override
        public void OnItemLongClickListener(int position, DiscoverBean bean, int flag) {

        }
    };

}
