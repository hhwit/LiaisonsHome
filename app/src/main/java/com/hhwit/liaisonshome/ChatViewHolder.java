package com.hhwit.liaisonshome;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Hongwen Huang on 2018/10/16
 * Email: hhwit@126.com
 */

public class ChatViewHolder extends RecyclerView.ViewHolder {
    public TextView words;

    ChatViewHolder(View itemView) {
        super(itemView);

        words = itemView.findViewById(R.id.item_chat_words);

    }
}
