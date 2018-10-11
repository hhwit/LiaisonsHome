package com.hhwit.liaisonshome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hongwen Huang on 2018/10/11
 * Email: hhwit@126.com
 */

public class TalksFragment extends Fragment {

    private RecyclerView mListView;
    private TalksAdapter mAdapter;
    private LinearLayoutManager mManager;

    public TalksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_talks, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = getView().findViewById(R.id.fragment_talks_view);

        mManager = new LinearLayoutManager(getContext());
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(mManager);

        mAdapter = new TalksAdapter(getActivity());
        mListView.setAdapter(mAdapter);

        initActions();

        List<TalksBean> mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TalksBean bean = new TalksBean();
            bean.setIdentity("China's foreign trade registered steady growth in the first eight months of this year despite higher tariffs imposed by the United States. " + Math.random());
            bean.setDate(new Date());
            if (i > 10) {
                bean.setDescription("hhw " + i);
            }
            bean.setContent("Growth in the first eight months of this year despite higher.");
            bean.setType((i % 4) + 1);
            bean.setWho(0);
            mList.add(bean);
        }
        mAdapter.addData(mList);
    };


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

    private ListViewCallback<TalksBean> listCallback = new ListViewCallback<TalksBean>() {
        @Override
        public void OnItemClickListener(int position, TalksBean bean, int flag) {
        }

        @Override
        public void OnItemLongClickListener(int position, TalksBean bean, int flag) {
        }
    };

}
