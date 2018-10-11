package com.hhwit.liaisonshome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hongwen Huang on 2018/10/11
 * Email: hhwit@126.com
 */

public class TalksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<TalksBean> mList = new ArrayList<>();
    private ListViewCallback<TalksBean> mListViewCallback;

    TalksAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = mInflater.inflate(R.layout.item_talks, parent, false);

        return new TalksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final TalksBean bean = mList.get(position);

        if (bean.getDescription() != null) {
            ((TalksViewHolder) holder).description.setText(bean.getDescription());
            ((TalksViewHolder) holder).description.setVisibility(View.VISIBLE);
        } else {
            ((TalksViewHolder) holder).description.setVisibility(View.GONE);
        }

        if (bean.getIdentity() != null) {
            ((TalksViewHolder) holder).identity.setText(bean.getIdentity());
        }

        ((TalksViewHolder) holder).type.setImage(bean.getType());

        if (bean.getContent() != null) {
            if (bean.getWho() == 0) { // me

            } else { // others

            }
            ((TalksViewHolder) holder).content.setText(bean.getContent());
        }

        Date date = bean.getDate();
        if (date != null) {
            String str = mContext.getResources().getString(R.string.talks_format_date_no_year);
            SimpleDateFormat format = new SimpleDateFormat(str);
            ((TalksViewHolder) holder).date.setText(format.format(date));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListViewCallback != null) {
                    mListViewCallback.OnItemClickListener(holder.getAdapterPosition(), bean, 0);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListViewCallback != null) {
                    mListViewCallback.OnItemLongClickListener(holder.getAdapterPosition(), bean, 0);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setCallback(ListViewCallback<TalksBean> callback) {
        mListViewCallback = callback;
    }

    public void addData(List<TalksBean> list) {
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
