package com.hhwit.liaisonshome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hongwen Huang on 2018/9/26
 * Email: hhwit@126.com
 */

public class DiscoverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int FOOTER_STATE_GONE = 0;
    public static final int FOOTER_STATE_LOADING = 1;
    public static final int FOOTER_STATE_NO_CONTENTS = 2;
    public static final int FOOTER_STATE_NETWORK_FAILED = 3;

    private static final int TYPE_CONTENT = 1;
    private static final int TYPE_FOOTER = 2;

    private LayoutInflater mInflater;
    private List<DiscoverBean> mList = new ArrayList<>();
    private ListViewCallback<DiscoverBean> mListViewCallback;
    private int mFooterState = FOOTER_STATE_GONE;


    DiscoverAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_CONTENT:
                view = mInflater.inflate(R.layout.item_discover, parent, false);
                break;
            case TYPE_FOOTER:
                view = mInflater.inflate(R.layout.footer_discover, parent, false);
                break;
        }
        return new DiscoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final int type = getItemViewType(position);
        if (type == TYPE_FOOTER) {
            switch (mFooterState) {
                case FOOTER_STATE_LOADING:
                    ((DiscoverViewHolder)holder).footerText.setText(R.string.discover_footer_loading);
                    break;
                case FOOTER_STATE_NO_CONTENTS:
                    ((DiscoverViewHolder)holder).footerText.setText(R.string.discover_footer_no_more);
                    break;
                case FOOTER_STATE_NETWORK_FAILED:
                    ((DiscoverViewHolder)holder).footerText.setText(R.string.discover_footer_network_failed);
                    break;
            }
            return;
        }

        if (mList == null) {
            return;
        }

        final DiscoverBean bean = mList.get(position);
        if (bean.getDescription() != null) {
            ((DiscoverViewHolder) holder).description.setText(bean.getDescription());
            ((DiscoverViewHolder) holder).description.setVisibility(View.VISIBLE);
        }
        if (bean.getIdentity() != null) {
            ((DiscoverViewHolder) holder).identity.setText(bean.getIdentity());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListViewCallback != null) {
                    mListViewCallback.OnItemClickListener(holder.getAdapterPosition(), bean, type);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListViewCallback != null) {
                    mListViewCallback.OnItemLongClickListener(holder.getAdapterPosition(), bean, type);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return position == mList.size()? TYPE_FOOTER : TYPE_CONTENT;
    }

    @Override
    public int getItemCount() {
        return mFooterState==FOOTER_STATE_GONE? mList.size() : mList.size() + 1;
    }

    public void setCallback(ListViewCallback<DiscoverBean> callback) {
        mListViewCallback = callback;
    }

    public void setFooterState(int state) {
        mFooterState = state;
        notifyDataSetChanged();
    }

    public void addData(List<DiscoverBean> list) {
        int size = mList.size();
        if (list != null && list.size() > 0) {
            mList.addAll(list);
            notifyItemRangeInserted(size, mList.size());
            notifyDataSetChanged();
        }
    }

    public void delData(int position) {
        if (mList.size() > position) {
            mList.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position);
        }
    }

    public void clearData() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

}
