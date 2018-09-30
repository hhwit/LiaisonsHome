package com.hhwit.liaisonshome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final int TYPE_CONTENT = 1;
    private static final int TYPE_FOOTER = 2;

    private Context mContext;
    private LayoutInflater mInflater;
    private boolean isLoading = false;
    private List<DiscoverBean> mList = new ArrayList<>();

    public DiscoverAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_FOOTER) {
            ((DiscoverViewHolder)holder).footerText.setText("footer");
            return;
        }

        DiscoverBean bean = mList.get(position);
        if (position > 10) {
            ((DiscoverViewHolder) holder).description.setText(bean.getDescription());
            ((DiscoverViewHolder) holder).description.setVisibility(View.VISIBLE);
        } else {
            ((DiscoverViewHolder) holder).description.setVisibility(View.GONE);
        }
        ((DiscoverViewHolder)holder).identity.setText(bean.getIdentity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return position == mList.size()? TYPE_FOOTER : TYPE_CONTENT;
    }

    @Override
    public int getItemCount() {
        return isLoading? mList.size() + 1 : mList.size();
    }

    public void addData(List<DiscoverBean> list) {
        if (list != null && list.size() > 0) {
            int pos = mList.size();
            mList.addAll(list);
            notifyItemRangeInserted(pos, list.size());
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
        mList.clear();
        notifyDataSetChanged();
    }

}
