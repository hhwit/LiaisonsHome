package com.hhwit.liaisonshome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    private SwipeRefreshLayout mSwipe;
    private RecyclerView mListView;
    private DiscoverAdapter mAdapter;
    private List<DiscoverBean> mList = new ArrayList<>();

    public DiscoverFragment() {
        // Required empty public constructor
    }


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

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(manager);

        mAdapter = new DiscoverAdapter(getActivity());
        mListView.setAdapter(mAdapter);

        for (int i = 0; i < 20; i++) {
            mList.add(new DiscoverBean("China's foreign trade registered steady growth in the first eight months of this year despite higher tariffs imposed by the United States.", "hhw"));
        }
        mAdapter.addData(mList);

    }

}
