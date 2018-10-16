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
 * Created by Hongwen Huang on 2018/10/16
 * Email: hhwit@126.com
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private List<ChatBean> mList = new ArrayList<>();
    private ListViewCallback<ChatBean> mListViewCallback;

    ChatAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ChatBean.TYPE_LEFT:
                view = mInflater.inflate(R.layout.item_chat_left, parent, false);
                break;
            case ChatBean.TYPE_MIDDLE:
                view = mInflater.inflate(R.layout.item_chat_middle, parent, false);
                break;
            case ChatBean.TYPE_RIGHT:
                view = mInflater.inflate(R.layout.item_chat_right, parent, false);
                break;
        }
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final int type = getItemViewType(position);

        final ChatBean bean = mList.get(position);
        if (bean.getWords() != null) {
            ((ChatViewHolder) holder).words.setText(bean.getWords());
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
        final ChatBean bean = mList.get(position);
        return bean.getType();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setCallback(ListViewCallback<ChatBean> callback) {
        mListViewCallback = callback;
    }

    public void addData(List<ChatBean> list) {
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
